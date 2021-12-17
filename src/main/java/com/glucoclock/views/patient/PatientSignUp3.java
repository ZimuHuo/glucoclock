package com.glucoclock.views.patient;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;


@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp3",layout = MainLayout.class)
public class PatientSignUp3 extends Div {

    private Select<String> diabetesSelect;
    private CheckboxGroup<String> insulinSelect;
    private CheckboxGroup<String> injectionSelect;
    private Button submitButton, previousButton;
    private VerticalLayout verticalLayout;
    private HorizontalLayout horizontalLayout;
    private MenuBar menu = new MenuBar("NS");
    private final PatientService patientService;
    public PatientSignUp3(PatientService patientService) {
        this.patientService = patientService;
        add(menu);
        init();
        HorizontalLayout hl = new HorizontalLayout();
        horizontalLayoutSetUp();
        verticalLayoutSetUp();
        hl.add(verticalLayout);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(hl);
    }

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




    private void diabetesSelectSetUp() {
        diabetesSelect = new Select<>("Type I", "Type II", "Gestational", "Others");
        diabetesSelect.setLabel("Type of diabetes");
        if (VaadinSession.getCurrent().getAttribute("DiabetesSelect")!= null){
            diabetesSelect.setValue((String) VaadinSession.getCurrent().getAttribute("Diabetes"));
        }
    }

    private void insulinSelectSetUp() {
        insulinSelect = new CheckboxGroup<>();
        insulinSelect.setLabel("Insulin type");
        insulinSelect.setItems("Rapid-acting insulin", "Short-acting insulin", "Intermediate-acting insulin", "Long-acting insulin");
        insulinSelect.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        if (VaadinSession.getCurrent().getAttribute("Insulin")!= null){
            insulinSelect.setValue((Set<String>) VaadinSession.getCurrent().getAttribute("Insulin"));
        }
    }

    private void injectionSelectSetUp() {
        injectionSelect = new CheckboxGroup<>();
        injectionSelect.setLabel("Injection method");
        injectionSelect.setItems("Syringe", "Injection pen", "Insulin pump");
        if (VaadinSession.getCurrent().getAttribute("Injection")!= null){
            injectionSelect.setValue((Set<String>) VaadinSession.getCurrent().getAttribute("Injection"));
        }
    }

    private void submitButtonInit() {
        submitButton = new Button("Sign Up");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
        submitButton.addClickListener(e -> {


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
                Patient patient = new Patient();
                patient.setFirstName((String)VaadinSession.getCurrent().getAttribute("FirstName"));
                patient.setLastName((String)VaadinSession.getCurrent().getAttribute("LastName"));
                patient.setEmail((String)VaadinSession.getCurrent().getAttribute("Email"));
                patient.setHomeAddress((String)VaadinSession.getCurrent().getAttribute("Email"));
                //database issue set string home address only one
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate("PatientStart")
                );
            }



        });
    }

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
