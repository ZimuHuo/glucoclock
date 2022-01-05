package com.glucoclock.views.patient;

import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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

    private final PatientService patientService;
    private final UserService userService;
    public PatientStartView(PatientService patientService, UserService userService){
        this.patientService = patientService;
        this.userService = userService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        authentication.getName();
        System.out.print(authentication.getName());

        UUID uid = userService.getRepository().findByUsername(authentication.getName()).getUid();
        String Logbook;
        add(menu);

        //create testing database



        LBtype = new ComboBox<>();
        LBtype.setLabel("Logbook\nType");
        LBtype.setItems("Simple","Comprehensive","Intensive");
        LBtype.addCustomValueSetListener(
                event -> LBtype.setValue(event.getDetail()));

        datePicker = new DatePicker("Date");
        Locale finnishLocale = new Locale("fi", "FI");
        datePicker.setLocale(finnishLocale);
        datePicker.setValue(LocalDate.now(ZoneId.systemDefault()));

        update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateButton = new Button(update);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.setWidth("120px");
        updateButton.setHeight("120px");

        LBtype.setValue(patientService.getRepository().getPatientByUid(uid).getLogbooktype());
        LBtype.addValueChangeListener(event -> {
            VaadinSession.getCurrent().setAttribute( "date",datePicker.getValue());
            if (event.getValue() == "Simple") {
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(SimpleLogbookView.class)
                        )
                );
            } else if (event.getValue() == "Comprehensive"){
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(ComprehensiveLogbookView.class)
                        )
                );
            }
            else if (event.getValue() == "Intensive"){
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(IntensiveLogbookView.class)
                        )
                );
            }
        });
        //setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER,title,LBtype,datePicker,updateButton);

        add(title,LBtype,datePicker,updateButton);
        if (VaadinSession.getCurrent().getAttribute("Error")!=null){
            com.vaadin.flow.component.notification.Notification notification = Notification.show("WRONG URL"+VaadinSession.getCurrent().getAttribute("Error"));
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            VaadinSession.getCurrent().setAttribute("Error",null);
        }
    }

}
