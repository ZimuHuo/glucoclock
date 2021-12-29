package com.glucoclock.views.patient;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.service.PatientService;

import com.glucoclock.security.db.Authorities;
import com.glucoclock.security.db.AuthoritiesService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.util.SendMail;
import com.google.gson.Gson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@PageTitle("Patient Sign Up")
@Route(value = "patient-sign-up-3")
public class PatientSignUp3 extends Div {

//    Components in the page
    private Select<String> diabetesSelect;
    private CheckboxGroup<String> insulinSelect;
    private Select<String> injectionSelect;
    private Button submitButton, previousButton;
    private VerticalLayout verticalLayout;
    private HorizontalLayout horizontalLayout;
    private MenuBar menu = new MenuBar("NS");
    private final UserService userService;
    private final PatientService patientService;
    private final AuthoritiesService authoritiesService;
    public PatientSignUp3(UserService userService, PatientService patientService, AuthoritiesService authoritiesService) {
        this.userService = userService;
        this.patientService = patientService;
        this.authoritiesService = authoritiesService;
        add(menu);
        init();
        HorizontalLayout hl = new HorizontalLayout();
        horizontalLayoutSetUp();
        verticalLayoutSetUp();
        hl.add(verticalLayout);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(hl);
    }

//    Initialize the components on the page
    private void init() {
        diabetesSelectSetUp();
        insulinSelectSetUp();
        injectionSelectSetUp();
        previousButtonInit();
        submitButtonInit();
    }

    private void horizontalLayoutSetUp() {
        this.horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(previousButton);
        horizontalLayout.add(submitButton);
    }

    private void verticalLayoutSetUp() {
        this.verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        verticalLayout.add(new H2("Info about your diabetes"));
        verticalLayout.add(
                diabetesSelect,
                insulinSelect,
                injectionSelect,
                horizontalLayout
        );
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        horizontalLayout.setWidth(verticalLayout.getWidth());
    }



//  Methods to initialize the components
//    Selection of diabetes type
    private void diabetesSelectSetUp() {
        diabetesSelect = new Select<>("Type I", "Type II", "Gestational", "Others");
        diabetesSelect.setLabel("Type of diabetes");
        if (VaadinSession.getCurrent().getAttribute("Diabetes")!= null){
            diabetesSelect.setValue(VaadinSession.getCurrent().getAttribute("Diabetes").toString());
        }
    }

//    Checkbox of insulin type
    private void insulinSelectSetUp() {
        insulinSelect = new CheckboxGroup<>();
        insulinSelect.setLabel("Insulin type");
        insulinSelect.setItems("Rapid-acting insulin", "Short-acting insulin", "Intermediate-acting insulin", "Long-acting insulin");
        insulinSelect.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        if (VaadinSession.getCurrent().getAttribute("Insulin")!= null){
            insulinSelect.setValue((Set<String>) VaadinSession.getCurrent().getAttribute("Insulin"));
        }
    }

//    Selection of injection method of insulin
    private void injectionSelectSetUp() {
        injectionSelect = new Select<>();
        injectionSelect.setLabel("Injection method");
        injectionSelect.setItems("Syringe", "Injection pen", "Insulin pump");
        if (VaadinSession.getCurrent().getAttribute("Injection")!= null){
            injectionSelect.setValue((String) VaadinSession.getCurrent().getAttribute("Injection"));
        }
    }

//    Button to complete sign up
    private void submitButtonInit() {
        submitButton = new Button("Sign Up");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
        submitButton.addClickListener(e -> {

//      Check if all fields have been filled in
            if (injectionSelect.isEmpty()) {
                Notification notification = Notification.show("Check Injection");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (insulinSelect.isEmpty()) {
                Notification notification = Notification.show("Check Insulin");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (diabetesSelect.isEmpty()) {
                Notification notification = Notification.show("Check DiabetesSelect");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else {

                VaadinSession.getCurrent().setAttribute( "Insulin",insulinSelect.getValue());
                VaadinSession.getCurrent().setAttribute( "Diabetes",diabetesSelect.getValue());
                VaadinSession.getCurrent().setAttribute( "Injection",injectionSelect.getValue());


//                Create and save a new user in db
                User user = new User(
                        (String)VaadinSession.getCurrent().getAttribute("Email"),
                        (String)VaadinSession.getCurrent().getAttribute("Password"),
                        "PATIENT",
                        (byte) 1
                        //Role.PATIENT,
                );
                userService.getRepository().save(user);


//                Create and save a new patient in db
                Patient patient = new Patient(
                        (String)VaadinSession.getCurrent().getAttribute("FirstName"),
                        (String)VaadinSession.getCurrent().getAttribute("LastName"),
                        (String)VaadinSession.getCurrent().getAttribute("Email"),
                        (String)VaadinSession.getCurrent().getAttribute("HomeAddressL1"),
                        (String)VaadinSession.getCurrent().getAttribute("HomeAddressL2"),
                        (String)VaadinSession.getCurrent().getAttribute("Postcode"),
                        (String)VaadinSession.getCurrent().getAttribute("City"),
                        (String)VaadinSession.getCurrent().getAttribute("ContactNumber"),
                        (String)VaadinSession.getCurrent().getAttribute("Sex"),
                        (LocalDate) VaadinSession.getCurrent().getAttribute("Date"),
                        (String)VaadinSession.getCurrent().getAttribute("Diabetes"),
                        insulinSelect.isSelected("Rapid-acting insulin"),
                        insulinSelect.isSelected("Short-acting insulin"),
                        insulinSelect.isSelected("Intermediate-acting insulin"),
                        insulinSelect.isSelected("Long-acting insulin"),
                        (String)VaadinSession.getCurrent().getAttribute("Injection"),
                        userService.getRepository().findByUsername((String)VaadinSession.getCurrent().getAttribute("Email")).getUid()
                );
                patientService.getRepository().save(patient);

//                Set the authority of the user as patient
                Authorities authorities = new Authorities((String)VaadinSession.getCurrent().getAttribute("Email"),"PATIENT");
                authoritiesService.getRepository().save(authorities);

//                Send a confirmation email
                SendMail.sendMail("Congratulations!","Thank you for choosing our app!",(String)VaadinSession.getCurrent().getAttribute("Email"));

//                Direct to patient home page
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientStart.class)
                );
            }



        });
    }

//    button to go back to last page
    private void previousButtonInit() {
        previousButton = new Button("Previous", new Icon(VaadinIcon.ARROW_LEFT));
        previousButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousButton.getElement().getStyle().set("margin-right", "auto");
        previousButton.addClickListener(e -> {

            VaadinSession.getCurrent().setAttribute( "Insulin",insulinSelect.getValue());
            VaadinSession.getCurrent().setAttribute( "Diabetes",diabetesSelect.getValue());
            VaadinSession.getCurrent().setAttribute( "Injection",injectionSelect.getValue());
            previousButton.getUI().ifPresent(ui ->
                    ui.navigate(PatientSignUp2.class)
            );

        });
    }
}
