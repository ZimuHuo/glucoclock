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
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;


//REMINDER:
//The function of cancel button is currently bugged.
//Need to fix it when programming backend and databases.

@PageTitle("Patient Settings")
@Route(value = "PatientSetting1",layout = MainLayout.class)
public class PatientSetting1 extends HorizontalLayout {


    //     These are sample variables
    //     Should be got from database
    //    --------------------------------------------
    String FName="Yifei";
    String LName="Jin";
    String Email="yj819@ic.ac.uk";
    String Home = "e";
    String PostCode="1";
    String Phone = "44 0421833";
    LocalDate Birth = LocalDate.of(2001,6,13);
    String Gender = "Male";
    String Diabetes = "Type I";
    Set<String> insulin;
    Set<String> injection;



    //    --------------------------------------------

//    All Components on the page
    TextField firstName;
    TextField lastName;
    DatePicker datePicker;
    EmailField emailField;
    TextField homeAddress;
    TextField postcode;
    TextField contactNumber;
    Select<String> genderSelect;
    Select<String> diabetesSelect;
    CheckboxGroup<String> insulinSelect;
    CheckboxGroup<String> injectionSelect;
    Button changeSetting, save, cancel, changePassword;

//   ----- Temporary button for testing database -----
//   -------------------------------------------------

    VerticalLayout MainLayout = new VerticalLayout();
    HorizontalLayout Buttons = new HorizontalLayout();

//    private final PatientService patientService;

    private MenuBar menu = new MenuBar("PNS");





//  Setting the layout of the page
    public PatientSetting1(PatientService patientService) {
        add(menu);
        //-----------------------------------------

//        this.patientService = patientService;
//        patientService.bulkcreate();
//        long id = 1;
//        Patient patient = patientService.getRepository().getPatientById(id);
//        FName = patient.getFirstName();
//        LName = patient.getLastName();
//        Email = patient.getEmail();
//        PostCode = patient.getPostCode();







        //-----------------------------------------







        init();







        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                datePicker, genderSelect,
                emailField, contactNumber,
                homeAddress
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("300px", 2)
        );
        formLayout.setColspan(firstName,1);
        formLayout.setColspan(lastName,1);
        formLayout.setColspan(datePicker,1 );
        formLayout.setColspan(emailField,1 );
        formLayout.setColspan(homeAddress,2 );
        formLayout.setColspan(contactNumber,1 );
        formLayout.setColspan(genderSelect,1 );

        Buttons.setWidth(MainLayout.getWidth());
        Buttons.add(changePassword, changeSetting, save, cancel);


        Button button = new Button("Check this");


        button.addClickListener(e ->{

            patientService.bulkcreate();
                }
        );
        MainLayout.add(
                new H1("Personal information"),
                formLayout,
                postcode,
                diabetesSelect,
                insulinSelect,
                injectionSelect,
                Buttons,
                button
        );
        MainLayout.setMaxWidth("600px");
        MainLayout.setPadding(false);

        setJustifyContentMode(JustifyContentMode.CENTER);





    }







    private void init() {
//      Initialize the components
        add(MainLayout);

        firstNameSetUp();
        lastNameSetUp();
        datePickerSetUp();
        emailFieldSetUp();
        homeAddressSetUp();
        postcodeSetUp();
        contactNumberSetUp();
        genderSelectSetUp();
        diabetesSelectSetUp();
        insulinSelectSetUp();
        injectionSelectSetUp();
        changeSettingSetUp();
        saveSetUp();
        cancelSetUp();
        changePasswordSetUp();



    }


//    Following functions are used to set up the components
    private void firstNameSetUp(){
        firstName = new TextField("First name");
        firstName.setValue(FName);
        firstName.setClearButtonVisible(true);
        firstName.setReadOnly(true);

    }


    private void lastNameSetUp() {
        lastName = new TextField("Last name");
        lastName.setValue(LName);
        lastName.setClearButtonVisible(true);
        lastName.setReadOnly(true);
    }

        private void contactNumberSetUp() {
        contactNumber = new TextField();
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
        contactNumber.setValue(Phone);
        contactNumber.setClearButtonVisible(true);
        contactNumber.setReadOnly(true);
    }

    private void emailFieldSetUp() {
        emailField = new EmailField();
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        emailField.setValue(Email);
        emailField.setReadOnly(true);
    }

    private void datePickerSetUp() {
        datePicker = new DatePicker("Date of birth");
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now);
        datePicker.setMin(now.minusYears(120));
        datePicker.setHelperText("You must be a minimum of 7 years old");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
        datePicker.setValue(Birth);
        datePicker.setReadOnly(true);
    }

    private void homeAddressSetUp() {
        homeAddress = new TextField("Home address");
        homeAddress.setValue(Home);
        homeAddress.setClearButtonVisible(true);
        homeAddress.setReadOnly(true);
    }

    private void postcodeSetUp() {
        postcode = new TextField("Postcode");
        postcode.setValue(PostCode);
        postcode.setClearButtonVisible(true);
        postcode.setReadOnly(true);
    }

    private void genderSelectSetUp() {
        genderSelect = new Select<>("Male","Female","Others","Prefer not to say");
        genderSelect.setValue(Gender);
        genderSelect.setLabel("Gender");
        genderSelect.setReadOnly(true);
    }

    private void diabetesSelectSetUp() {
        diabetesSelect = new Select<>("Type I","Type II");
        diabetesSelect.setValue(Diabetes);
        diabetesSelect.setLabel("Type of diabetes");
        diabetesSelect.setReadOnly(true);
    }

    private void insulinSelectSetUp() {
        insulinSelect = new CheckboxGroup<>();
        insulinSelect.setLabel("Insulin type");
        insulinSelect.setItems("Rapid-acting insulin","Short-acting insulin","Intermediate-acting insulin","Long-acting insulin");
        insulinSelect.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        insulinSelect.select("Rapid-acting insulin","Short-acting insulin","Intermediate-acting insulin");
        insulinSelect.setReadOnly(true);
    }

    private void injectionSelectSetUp() {
        injectionSelect = new CheckboxGroup<>();
        injectionSelect.setLabel("Injection method");
        injectionSelect.setItems("Syringe", "Injection pen", "Insulin pump");
        injectionSelect.select("Injection pen");
        injectionSelect.setReadOnly(true);
    }

    private void changeSettingSetUp() {
        changeSetting = new Button("Change Info");
        changeSetting.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        changeSetting.getElement().getStyle().set("margin-left", "auto");
        changeSetting.addClickListener(e -> {
            allSetReadOnly(false);
            changeSetting.setVisible(false);
            changePassword.setVisible(false);
            save.setVisible(true);
            cancel.setVisible(true);
        });

    }

    private void saveSetUp() {
        save = new Button("Save");
        save.setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-left", "1em");
        save.addClickListener(e -> {
            FName = firstName.getValue();
            LName = lastName.getValue();
            Email = emailField.getValue();
            Home = homeAddress.getValue();
            PostCode = postcode.getValue();
            Phone = contactNumber.getValue();
            Birth = datePicker.getValue();
            Gender = genderSelect.getValue();
            Diabetes = diabetesSelect.getValue();
            insulin = insulinSelect.getValue();
            injection = injectionSelect.getValue();
            allSetReadOnly(true);
            changeSetting.setVisible(true);
            changePassword.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);



            long id = 1;
            //patientService.updatePatientLastName(id,lastName.getValue());


            Notification.show("Changes saved",2000, Notification.Position.TOP_CENTER);
        });
    }

    private void cancelSetUp() {
        cancel = new Button("Cancel");
        cancel.setVisible(false);
        cancel.getElement().getStyle().set("margin-right", "0");
        cancel.addClickListener(e -> {
            firstName.setValue(FName);
            lastName.setValue(LName);
            datePicker.setValue(Birth);
            emailField.setValue(Email);
            homeAddress.setValue(Home);
            postcode.setValue(PostCode);
            contactNumber.setValue(Phone);
            genderSelect.setLabel(Gender);
            diabetesSelect.setLabel(Diabetes);
            insulinSelect.select(insulin);
            injectionSelect.select(injection);

            changeSetting.setVisible(true);
            changePassword.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            allSetReadOnly(true);
            Notification.show("Changes cancelled",2000, Notification.Position.TOP_CENTER);
        });
    }

    private void changePasswordSetUp() {
        changePassword = new Button("Change Password");
        changePassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-right", "auto");
        changePassword.addClickListener(e ->
                changePassword.getUI().ifPresent(ui ->
                        ui.navigate(PatientSetting2.class)
                )
        );
    }


//    Set all the components on the page to be read-only or not
    private void allSetReadOnly(boolean Boolean) {
        firstName.setReadOnly(Boolean);
        lastName.setReadOnly(Boolean);
        datePicker.setReadOnly(Boolean);
        emailField.setReadOnly(Boolean);
        homeAddress.setReadOnly(Boolean);
        postcode.setReadOnly(Boolean);
        contactNumber.setReadOnly(Boolean);
        genderSelect.setReadOnly(Boolean);
        diabetesSelect.setReadOnly(Boolean);
        insulinSelect.setReadOnly(Boolean);
        injectionSelect.setReadOnly(Boolean);
    }
}