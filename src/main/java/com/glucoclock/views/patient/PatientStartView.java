package com.glucoclock.views.patient;

import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;

@PageTitle("Start Page")
@Route(value = "/patient/start-page")
@RolesAllowed("PATIENT")
public class PatientStartView extends VerticalLayout{
    private ComboBox<String> LBtype;
    private DatePicker datePicker;
    private Icon update;
    private Button updateButton;
    private MenuBar menu = new MenuBar("PStart");
    private H2 title = new H2("Upload Logbook Entry");
    private UUID uid;
    private Notification notification;

    private final PatientService patientService;
    private final UserService userService;
    private final LogService logService;
    private final NotificationService notificationService;
    public PatientStartView(PatientService patientService, UserService userService, LogService logService, NotificationService notificationService){
        this.patientService = patientService;
        this.userService = userService;
        this.logService = logService;
        this.notificationService = notificationService;

        //get patient uid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        authentication.getName();
        uid = userService.getRepository().findByUsername(authentication.getName()).getUid();
        add(menu);

        //suggested logbook type
        String lbType = patientService.getRepository().getPatientByUid(uid).getLogbooktype();

        //Get the number of unresolved messages and display if not zero
        Integer unresolvedN = notificationService.countUnresolvedMsg(uid,"PATIENT");
        if(unresolvedN!=0){
            Button close = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            Div msg = new Div(new Text("You have "+unresolvedN+" unresolved notification(s)."));
            HorizontalLayout noti = new HorizontalLayout(new Icon(VaadinIcon.ENVELOPES),msg,close);
            noti.setAlignItems(Alignment.CENTER);
            close.addClickListener(e->notification.close());
            notification = new Notification();
            notification.add(noti);
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.TOP_CENTER);
            notification.open();
            notification.setDuration(5000);
        }


    //UI components set up
        //Date
        datePicker = new DatePicker("Date");
        Locale finnishLocale = new Locale("fi", "FI");
        datePicker.setLocale(finnishLocale);
        datePicker.setValue(LocalDate.now(ZoneId.systemDefault()));
        datePicker.setMax(LocalDate.now());
        //Logbook type
        LBtype = new ComboBox<>();
        LBtype.setLabel("Logbook Type");
            //initialise
        checkCurrentLog(LocalDate.now(),lbType);

        //Upload
        update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateButton = new Button(update);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.setWidth("120px");
        updateButton.setHeight("120px");

        //Check if there's already data at the date selected
        datePicker.addValueChangeListener(click->{
            checkCurrentLog(datePicker.getValue(),lbType);
        });

        //show suggested logbook type if it's not N/A
        add(title);
        if(!lbType.equals("N/A")){
            Span suggestedLb = new Span("Suggested Logbook Type: " + lbType);
            suggestedLb.getElement().getThemeList().add("badge success");
            add(suggestedLb);
        }

        //Update Button
        updateButton.addClickListener(e ->{
            VaadinSession.getCurrent().setAttribute( "date",datePicker.getValue());
            if (LBtype.getValue().equals("Simple")) {
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(SimpleLogbookView.class)
                        );
            } else if (LBtype.getValue().equals("Comprehensive")){
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(ComprehensiveLogbookView.class)
                        );
            }
            else if (LBtype.getValue().equals("Intensive")){
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(IntensiveLogbookView.class)
                        );
            }
                }
        );


        setAlignItems(Alignment.CENTER);
        add(datePicker,LBtype,updateButton);
        if (VaadinSession.getCurrent().getAttribute("Error")!=null){
            com.vaadin.flow.component.notification.Notification notification = Notification.show("WRONG URL"+VaadinSession.getCurrent().getAttribute("Error"));
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            VaadinSession.getCurrent().setAttribute("Error",null);
        }
    }

    //check in the log_db, if there is already a selected logbook type (already entered a data)
    //display only that logbook type
    public void checkCurrentLog(LocalDate checkdate, String suggestlog){
        Log checkLog;
        //check is there already logbook at this day
        checkLog=logService.findLogBookbyDate(checkdate,uid);

        if(checkLog==null){
            //if no logbook entered this day
            //all options are available
            LBtype.setItems("Simple","Comprehensive","Intensive");
            //show doctor suggest log
            if(!suggestlog.equals("N/A")) LBtype.setValue(suggestlog);
        }
        else if(checkLog.getLogbooktype()==1){
            //simple logbook already entered this day
            LBtype.setItems("Simple");
            LBtype.setValue("Simple");
        }
        else if(checkLog.getLogbooktype()==2){
            //comprehensive logbook already entered this day
            LBtype.setItems("Comprehensive");
            LBtype.setValue("Comprehensive");
        }
        else if(checkLog.getLogbooktype()==3){
            //intensive logbook already entered this day
            LBtype.setItems("Intensive");
            LBtype.setValue("Intensive");
        }
    }

}
