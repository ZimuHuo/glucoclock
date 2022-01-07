package com.glucoclock.views.doctor;

import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.util.UUID;

@PageTitle("Suggest Logbook Type")
@Route(value = "doctor/suggest-logbook-type")
public class SuggestLogbookTypeView extends Div {
    private Select<String> logbookSuggestion = new Select<>();
    private Button confirm = new Button("Confirm");
    private MenuBar menu = new MenuBar("DNS");

    private final UserService userService;
    private final DoctorPatientService doctorpatientService;
    private final PatientService patientService;
    private final DoctorService doctorService;


    public SuggestLogbookTypeView(UserService userService, DoctorPatientService doctorpatientService, PatientService patientService, DoctorService doctorService){
        this.userService = userService;
        this.doctorpatientService = doctorpatientService;
        this.patientService = patientService;
        this.doctorService = doctorService;

        H3 title = new H3("Suggest Logbook Type for " +
                patientService.getRepository().getPatientByUid((UUID) VaadinSession.getCurrent().getAttribute("PatientUID")).getFirstName()
        +" "+patientService.getRepository().getPatientByUid((UUID) VaadinSession.getCurrent().getAttribute("PatientUID")).getLastName());

        logbookSuggestion.setItems("Simple","Comprehensive","Intensive");
        String suggestedLb = patientService.getRepository().getPatientByUid((UUID) VaadinSession.getCurrent().getAttribute("PatientUID")).getLogbooktype();
        if(!suggestedLb.equals("N/A")){
            logbookSuggestion.setValue(suggestedLb);
        }

        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        confirm.addClickListener(e->{
                        patientService.updateLogbookType((UUID) VaadinSession.getCurrent().getAttribute("PatientUID"),logbookSuggestion.getValue());
                        Notification.show("Suggested Logbook Type Updated!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                confirm.getUI().ifPresent(ui->ui.navigate(DoctorStartView.class));}
                );


        VerticalLayout vl = new VerticalLayout();
        vl.add(title,logbookSuggestion,confirm);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);

        add(menu,vl);

    }
}
