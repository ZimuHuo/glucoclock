package com.glucoclock.views.doctor;

import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@PageTitle("Add Patient")
@Route(value = "doctor/add-patient")
public class AddPatientView extends Div {
    private H2 title = new H2("Add Patient");
    private TextField patientEmail = new TextField("Enter Patient Email");
    private Icon searchIcon = new Icon(VaadinIcon.SEARCH);
    private Button search = new Button(searchIcon);
    private Button add = new Button("Add Patient");
    private MenuBar menu = new MenuBar("DNS");

    private final DoctorPatientService doctorpatientService;
    private final PatientService patientService;

    private UUID patientuid;
    private String searchEmail;
    private int status=0;
    //defalt=0, =1 if there is data already inside the database, do not need to enter again.
    private UUID doctoruid=UUID.fromString("58864138-61ab-49c5-97ef-c98f8c981b0e");



    public AddPatientView(DoctorPatientService doctorpatientService, PatientService patientService){
        this.doctorpatientService = doctorpatientService;
        this.patientService = patientService;

        //buttons style
        searchIcon.setColor("white");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.setEnabled(false);//initially the button is disabled

        //Search--Patient found notification upon click
        search.addClickListener(e->{
            //get the value in the textfield when click search button
            searchEmail=patientEmail.getValue();
            //get uid of this email
            patientuid=this.patientService.searchPatientuid(searchEmail);
            if(patientuid==null)status=3; //(this patient do not have an account)
            else {
                List<UUID> uidList = new ArrayList<>();
                //check if this email already exist in this doctor's patient list
                uidList = doctorpatientService.getPatientidlist(doctoruid);
                for (UUID thisid : uidList) {
                    if (thisid.equals(patientuid)) status = 1; //(this id is already exist in the database)
                    else status = 2;   //(this id not exist in current list)
                }
            }
            //notifications
            if(status==1)Notification.show("Patient Already Exist").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            if(status==2)Notification.show("Patient Found").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            if(status==3)Notification.show("Patient Not Exist").addThemeVariants(NotificationVariant.LUMO_ERROR);
            //enable the add button, when the patient can be added
            if(status==2) add.setEnabled(true);
        });


        //Add--Click add to go back to home page
        add.addClickListener(e->{
            //add to database
            this.doctorpatientService.create(patientuid,doctoruid);
            Notification.show("Successfully Added").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            //navigate to Patient start page
            add.getUI().ifPresent(ui ->
                            ui.navigate(DoctorStartView.class));
                }
        );

        //Page layout
        //Search for patient row
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(patientEmail,search);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        //Entire layout
        VerticalLayout vl = new VerticalLayout();
        vl.add(title, hl, add);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        add(menu,vl);
    }
}
