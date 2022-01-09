package com.glucoclock.views.doctor;

import com.glucoclock.database.doctorpatient_db.model.DoctorPatient;
import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@PageTitle("My Patients")
@Route(value = "doctor/my-patients")
public class DoctorStartView extends VerticalLayout {
    private Grid<PatientInfo> grid;
    private ListDataProvider<PatientInfo> dataProvider;

    private Grid.Column<PatientInfo> firstNameColumn;
    private Grid.Column<PatientInfo> lastNameColumn;
    private Grid.Column<PatientInfo> emailColumn;
    private Grid.Column<PatientInfo> logbookColumn;
    private Grid.Column<PatientInfo> buttonColumn;
    private Grid.Column<PatientInfo> buttonDelete;

    private MenuBar menu = new MenuBar("DStart");

    private HorizontalLayout hl = new HorizontalLayout();
    private H3 title = new H3("My Patients");
    private Icon add = new Icon(VaadinIcon.PLUS_CIRCLE);
    private Button addBut = new Button(add);
    private Notification notification;

    //database
    private final UserService userService;
    private final DoctorPatientService doctorpatientService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NotificationService notificationService;

    //need to connect to session
    private UUID doctoruid;


    public DoctorStartView(UserService userService, DoctorPatientService doctorpatientService, PatientService patientService, DoctorService doctorService, NotificationService notificationService) {
        //database
        this.userService = userService;
        this.doctorpatientService = doctorpatientService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.notificationService = notificationService;

        //get doctor id using authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        String username = authentication.getName();//return email
        User user=userService.getRepository().findByUsername(username); //return user
        doctoruid=user.getUid();

        //add button
        add.setSize("50px");
        addBut.setWidth("55px");
        addBut.setHeight("55px");
        addBut.addClickListener(e->
                //navigate to the add patient page
                addBut.getUI().ifPresent(ui ->
                        ui.navigate(AddPatientView.class))
        );

        hl.add(title,addBut);
        hl.setHeight("20%");
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE,title,addBut);

        //Create grid
        createGrid();

        //Layout
        VerticalLayout vl = new VerticalLayout();
        vl.add(hl);
        vl.setPadding(true);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        add(vl,grid,menu);
        setPadding(true);
        setMargin(true);

        //Get the number of unresolved messages and display if not zero
        Integer unresolvedN = this.notificationService.countUnresolvedMsg(doctoruid,"DOCTOR");
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

        if (VaadinSession.getCurrent().getAttribute("Error")!=null){
            com.vaadin.flow.component.notification.Notification notification = Notification.show("WRONG URL"+VaadinSession.getCurrent().getAttribute("Error"));
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            VaadinSession.getCurrent().setAttribute("Error",null);
        }
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.setAllRowsVisible(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        //get patient list of this doctor
        dataProvider = new ListDataProvider<>(getPatients());
        grid.setDataProvider(dataProvider);
        grid.setAllRowsVisible(true);
    }


    private void addColumnsToGrid() {
        firstNameColumn = grid.addColumn(PatientInfo::getFirstName, "FirstName").setHeader("First Name").setWidth("17%").setFlexGrow(0);
        lastNameColumn = grid.addColumn(PatientInfo::getLastName, "LastName").setHeader("Last Name").setWidth("17%").setFlexGrow(0);
        emailColumn = grid.addColumn(PatientInfo::getEmail, "Email").setHeader("Email").setWidth("28%").setFlexGrow(0);
        logbookColumn = grid.addComponentColumn(PatientInfo::buildEditLbTypeButton).setHeader("Suggested Logbook Type").setWidth("18%").setFlexGrow(0);
        buttonColumn = grid.addComponentColumn(PatientInfo::buildViewButton).setWidth("11%").setFlexGrow(0);
        buttonDelete = grid.addComponentColumn(PatientInfo::deletePatient).setWidth("6%").setFlexGrow(0);
    }



    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField firstNameFilter = new TextField();
        firstNameFilter.setPlaceholder("Filter");
        firstNameFilter.setClearButtonVisible(true);
        firstNameFilter.setWidth("100%");
        firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(patientInfo -> StringUtils.containsIgnoreCase(patientInfo.getFirstName(), firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder("Filter");
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(patientInfo -> StringUtils.containsIgnoreCase(patientInfo.getLastName(), lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);

        TextField emailFilter = new TextField();
        emailFilter.setPlaceholder("Filter");
        emailFilter.setClearButtonVisible(true);
        emailFilter.setWidth("100%");
        emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        emailFilter.addValueChangeListener(event -> dataProvider
                .addFilter(patientInfo -> StringUtils.containsIgnoreCase(patientInfo.getEmail(), emailFilter.getValue())));
        filterRow.getCell(emailColumn).setComponent(emailFilter);

    }


    private List<PatientInfo> getPatients() {
        //get patients from the database
        List<PatientInfo> patientList_final = new ArrayList<>();
        List<DoctorPatient> patientList;

        //check patient list of a doctor in the DoctorPateint database using doctoruid
        patientList=doctorpatientService.getPatientlist(doctoruid);
        //change each patient into PatientInfo form, add to patientList_final
        for(DoctorPatient thispatient:patientList){
            PatientInfo p = new PatientInfo(patientService.searchByuid(thispatient.getPatientuid()).getFirstName(),
                    patientService.searchByuid(thispatient.getPatientuid()).getLastName(),
                    patientService.searchByuid(thispatient.getPatientuid()).getEmail(),
                    patientService.searchByuid(thispatient.getPatientuid()).getLogbooktype(),
                    thispatient.getPatientuid(),
                    doctorpatientService, patientService);
            patientList_final.add(p);
        }

        //return list of patient of the doctor
        return patientList_final;

    }

}
