package com.glucoclock.views.doctor;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.patient.PatientStart;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

@PageTitle("Doctor Settings")
@Route(value = "DoctorSetting1",layout = MainLayout.class)

public class DoctorSetting1 extends HorizontalLayout {


    //    All variables
    String FName;
    String LName;
    String Email;
    String Home;
    String PostCode;
    String Phone;
    LocalDate Birth;
    String Gender;

    //    All Components on the page
    TextField firstName, lastName, homeAddress, postcode, contactNumber;
    DatePicker birthSelect;
    EmailField emailField;
    Select<String> genderSelect;
    Button changeSetting, save, cancel, changePassword, toHome;
    VerticalLayout mainLayout;
    HorizontalLayout buttons;

    DoctorService doctorService;
    
    
    public DoctorSetting1(DoctorService doctorService) {

        //-----------------------------------------

        this.doctorService = doctorService;
        doctorService.bulkcreate();
        long id = 1;
        Doctor doctor = doctorService.getRepository().getDoctorById(id);


        //-----------------------------------------
        
        
        init(doctor);
        setJustifyContentMode(JustifyContentMode.CENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                birthSelect, genderSelect,
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
        formLayout.setColspan(birthSelect,1 );
        formLayout.setColspan(emailField,1 );
        formLayout.setColspan(homeAddress,2 );
        formLayout.setColspan(contactNumber,1 );
        formLayout.setColspan(genderSelect,1 );

        buttons.add(changePassword, changeSetting, save, cancel);

        mainLayout.add(
                new H1("Personal Information"),
                formLayout,
                postcode,
                buttons,
                toHome
        );

        add(mainLayout);
    }


//    initialize all components
    private void init(Doctor doctor) {
        FName = doctor.getFirstName();
        LName = doctor.getLastName();
        Email = doctor.getEmail();
        PostCode = doctor.getPostCode();
        Home = doctor.getHomeAddress();
        Gender = doctor.getGender();
        Phone = doctor.getPhone();
        Birth = doctor.getBirthday();
        
        mainLayoutInit();
        buttonsInit();
        firstNameInit();
        lastNameInit();
        birthSelectInit();
        emailFieldInit();
        homeAddressInit();
        postcodeInit();
        contactNumberInit();
        genderSelectInit();
        changeSettingInit();
        saveInit(doctor.getId());
        cancelInit();
        changePasswordInit();
        toHomeSetUp();
    }

//    Each of following methods initialize one component
    private void mainLayoutInit() {
        mainLayout = new VerticalLayout();
        mainLayout.setMaxWidth("600px");
    }

    private void buttonsInit() {
        buttons = new HorizontalLayout();
        buttons.setWidth(mainLayout.getWidth());
    }

    private void firstNameInit() {
        firstName = new TextField("First name");
        firstName.setValue(FName);
        firstName.setClearButtonVisible(true);
        firstName.setReadOnly(true);
    }

    private void lastNameInit() {
        lastName = new TextField("Last name");
        lastName.setValue(LName);
        lastName.setClearButtonVisible(true);
        lastName.setReadOnly(true);
    }
    private void contactNumberInit() {
        contactNumber = new TextField();
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
        contactNumber.setValue(Phone);
        contactNumber.setClearButtonVisible(true);
        contactNumber.setReadOnly(true);
    }

    private void emailFieldInit() {
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

    private void birthSelectInit() {
        birthSelect = new DatePicker("Date of birth");
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        birthSelect.setMax(now);
        birthSelect.setMin(now.minusYears(120));
        birthSelect.setHelperText("You must be a minimum of 7 years old");
        birthSelect.setHelperText("Format: DD.MM.YYYY");
        birthSelect.setPlaceholder("DD.MM.YYYY");
        birthSelect.setValue(Birth);
        birthSelect.setReadOnly(true);
    }

    private void homeAddressInit() {
        homeAddress = new TextField("Home address");
        homeAddress.setValue(Home);
        homeAddress.setClearButtonVisible(true);
        homeAddress.setReadOnly(true);
    }

    private void postcodeInit() {
        postcode = new TextField("Postcode");
        postcode.setValue(PostCode);
        postcode.setClearButtonVisible(true);
        postcode.setReadOnly(true);
    }

    private void genderSelectInit() {
        genderSelect = new Select<>("Male","Female","Others","Prefer not to say");
        genderSelect.setValue(Gender);
        genderSelect.setLabel("Gender");
        genderSelect.setReadOnly(true);
    }

    private void changeSettingInit() {
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

    private void saveInit(long id) {
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
            Birth = birthSelect.getValue();
            Gender = genderSelect.getValue();

//            update any changes to the database
            doctorService.updateDoctorFirstName(id,FName);
            doctorService.updateDoctorLastName(id, LName);
            doctorService.updateDoctorEmail(id, Email);
            doctorService.updateDoctorAddress(id, Home);
            doctorService.updateDoctorPostCode(id, PostCode);
            doctorService.updateDoctorPhone(id, Phone);
            doctorService.updateDoctorBirthday(id, Birth);
            doctorService.updateDoctorGender(id, Gender);

//            Change the accessibility and appearance when saved
            allSetReadOnly(true);
            changeSetting.setVisible(true);
            changePassword.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);


            Notification.show("Changes saved",2000, Notification.Position.TOP_CENTER);
        });
    }

    private void cancelInit() {
        cancel = new Button("Cancel");
        cancel.setVisible(false);
        cancel.getElement().getStyle().set("margin-left", "auto");
        cancel.addClickListener(e -> {
            firstName.setValue(FName);
            lastName.setValue(LName);
            birthSelect.setValue(Birth);
            emailField.setValue(Email);
            homeAddress.setValue(Home);
            postcode.setValue(PostCode);
            contactNumber.setValue(Phone);
            genderSelect.setValue(Gender);

            changeSetting.setVisible(true);
            changePassword.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            allSetReadOnly(true);
            Notification.show("Changes cancelled",2000, Notification.Position.TOP_CENTER);
        });
    }

    private void changePasswordInit() {
        changePassword = new Button("Change Password");
        changePassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-right", "auto");
        changePassword.addClickListener(e ->
                changePassword.getUI().ifPresent(ui ->
                        ui.navigate(DoctorSetting2.class)
                )
        );
    }

    private void toHomeSetUp() {
        toHome = new Button("OK");
        toHome.getElement().getStyle().set("margin-left", "auto");
        toHome.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        toHome.addClickListener(e -> {
            toHome.getUI().ifPresent(ui ->
                    ui.navigate(PatientStart.class)
            );
        });
    }


    //    Set all the components on the page to be read-only or not
    private void allSetReadOnly(boolean Boolean) {
        firstName.setReadOnly(Boolean);
        lastName.setReadOnly(Boolean);
        birthSelect.setReadOnly(Boolean);
        emailField.setReadOnly(Boolean);
        homeAddress.setReadOnly(Boolean);
        postcode.setReadOnly(Boolean);
        contactNumber.setReadOnly(Boolean);
        genderSelect.setReadOnly(Boolean);
    }
}
