package com.glucoclock.views.doctor;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@PageTitle("Settings")
@Route(value = "/doctor/settings")

public class DoctorSettings1View extends HorizontalLayout {


    //    All variables
    String FName;
    String LName;
    String Email;
    String AddressL1;
    String AddressL2;
    String PostCode;
    String City;
    String Phone;
    LocalDate Birth;
    String Gender;

    //    All Components on the page
    private H1 space = new H1(" ");
    TextField firstName, lastName, homeAddressL1, homeAddressL2, postcode, cityField, contactNumber;
    DatePicker birthSelect;
    EmailField emailField;
    Select<String> genderSelect;
    Button changeSetting, save, cancel, changePassword;
    VerticalLayout mainLayout;
    HorizontalLayout buttons;
    private MenuBar menu = new MenuBar("DNS");

    private final DoctorService doctorService;
    private final UserService userService;
    
    
    public DoctorSettings1View(DoctorService doctorService, UserService userService) {

        this.doctorService = doctorService;
        this.userService = userService;

//        The uid of current user
        UUID uid = userService.getRepository().findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUid();


        init(doctorService.getRepository().getDoctorByUid(uid));
        setJustifyContentMode(JustifyContentMode.CENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                birthSelect, genderSelect,
                emailField, contactNumber,
                homeAddressL1,
                homeAddressL2
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 300px
                new FormLayout.ResponsiveStep("300px", 2)
        );
        formLayout.setColspan(firstName,1);
        formLayout.setColspan(lastName,1);
        formLayout.setColspan(birthSelect,1 );
        formLayout.setColspan(emailField,1 );
        formLayout.setColspan(homeAddressL1,2 );
        formLayout.setColspan(homeAddressL2,2 );
        formLayout.setColspan(contactNumber,1 );
        formLayout.setColspan(genderSelect,1 );

        buttons.add(changePassword, changeSetting, save, cancel);

        mainLayout.add(space,
                new H2("Settings"),
                buttons,
                formLayout,
                postcode,
                cityField
                //toHome
        );

        add(
                menu,
                mainLayout
        );
    }


//    initialize all components
    private void init(Doctor doctor) {
        FName = doctor.getFirstName();
        LName = doctor.getLastName();
        Email = doctor.getEmail();
        PostCode = doctor.getPostCode();
        AddressL1 = doctor.getHomeAddressL1();
        AddressL2 = doctor.getHomeAddressL2();
        City = doctor.getCity();
        Gender = doctor.getGender();
        Phone = doctor.getPhone();
        Birth = doctor.getBirthday();
        
        mainLayoutInit();
        buttonsInit();
        firstNameInit();
        lastNameInit();
        birthSelectInit();
        emailFieldInit();
        homeAddressL1Init();
        homeAddressL2Init();
        postcodeInit();
        cityFieldInit();
        contactNumberInit();
        genderSelectInit();
        changeSettingInit();
        saveInit(doctor.getUid());
        cancelInit();
        changePasswordInit();
        //toHomeSetUp();
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

    private void homeAddressL1Init() {
        homeAddressL1 = new TextField("Address L1");
        homeAddressL1.setValue(AddressL1);
        homeAddressL1.setClearButtonVisible(true);
        homeAddressL1.setReadOnly(true);
    }

    private void homeAddressL2Init() {
        homeAddressL2 = new TextField("Address L2");
        homeAddressL2.setValue(AddressL2);
        homeAddressL2.setClearButtonVisible(true);
        homeAddressL2.setReadOnly(true);
    }

    private void postcodeInit() {
        postcode = new TextField("Postcode");
        postcode.setValue(PostCode);
        postcode.setClearButtonVisible(true);
        postcode.setReadOnly(true);
    }

    private void cityFieldInit() {
        cityField = new TextField("City");
        cityField.setValue(City);
        cityField.setClearButtonVisible(true);
        cityField.setReadOnly(true);
    }

    private void genderSelectInit() {
        genderSelect = new Select<>("Male","Female","Others","Prefer not to say");
        genderSelect.setValue(Gender);
        genderSelect.setLabel("Gender");
        genderSelect.setReadOnly(true);
    }

    private void changeSettingInit() {
        changeSetting = new Button("Edit Info");
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

    private void saveInit(UUID uid) {
        save = new Button("Save");
        save.setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-left", "1em");
        save.addClickListener(e -> {
            if(firstName.isEmpty() || lastName.isEmpty() || emailField.isEmpty() || emailField.isInvalid() || genderSelect.isEmpty() || homeAddressL1.isEmpty() || postcode.isEmpty() || cityField.isEmpty() || contactNumber.isEmpty() || birthSelect.isEmpty()) {
                //Show the error messages
                if (firstName.isEmpty()){
                    Notification notification = Notification.show("First name is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (lastName.isEmpty()){
                    Notification notification = Notification.show("Last name is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (emailField.isEmpty() || emailField.isInvalid()){
                    Notification notification = Notification.show("A valid email is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (genderSelect.isEmpty()){
                    Notification notification = Notification.show("Gender is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (homeAddressL1.isEmpty()){
                    Notification notification = Notification.show("Your address is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (postcode.isEmpty()){
                    Notification notification = Notification.show("Postcode is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (cityField.isEmpty()){
                    Notification notification = Notification.show("City is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (contactNumber.isEmpty()){
                    Notification notification = Notification.show("Phone number is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (birthSelect.isEmpty()){
                    Notification notification = Notification.show("Birthday is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

            } else {
                FName = firstName.getValue();
                LName = lastName.getValue();
                Email = emailField.getValue();
                AddressL1 = homeAddressL1.getValue();
                AddressL2 = homeAddressL2.getValue();
                PostCode = postcode.getValue();
                City = cityField.getValue();
                Phone = contactNumber.getValue();
                Birth = birthSelect.getValue();
                Gender = genderSelect.getValue();

//            update any changes to the database
                doctorService.updateDoctorFirstName(uid,FName);
                doctorService.updateDoctorLastName(uid, LName);
                doctorService.updateDoctorEmail(uid, Email);
                doctorService.updateDoctorAddressL1(uid, AddressL1);
                doctorService.updateDoctorAddressL2(uid, AddressL2);
                doctorService.updateDoctorPostCode(uid, PostCode);
                doctorService.updateDoctorCity(uid, City);
                doctorService.updateDoctorPhone(uid, Phone);
                doctorService.updateDoctorBirthday(uid, Birth);
                doctorService.updateDoctorGender(uid, Gender);

//            Change the accessibility and appearance when saved
                allSetReadOnly(true);
                changeSetting.setVisible(true);
                changePassword.setVisible(true);
                save.setVisible(false);
                cancel.setVisible(false);


                Notification.show("Changes saved",2000, Notification.Position.TOP_CENTER);
            }

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
            homeAddressL1.setValue(AddressL1);
            homeAddressL2.setValue(AddressL2);
            postcode.setValue(PostCode);
            cityField.setValue(City);
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
                        ui.navigate(DoctorSettings2View.class)
                )
        );
    }

//    private void toHomeSetUp() {
//        toHome = new Button("OK");
//        toHome.getElement().getStyle().set("margin-left", "auto");
//        toHome.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        toHome.addClickListener(e -> {
//            toHome.getUI().ifPresent(ui ->
//                    ui.navigate(DoctorStartView.class)
//            );
//        });
//    }


    //    Set all the components on the page to be read-only or not
    private void allSetReadOnly(boolean Boolean) {
        firstName.setReadOnly(Boolean);
        lastName.setReadOnly(Boolean);
        birthSelect.setReadOnly(Boolean);
        homeAddressL1.setReadOnly(Boolean);
        homeAddressL2.setReadOnly(Boolean);
        postcode.setReadOnly(Boolean);
        cityField.setReadOnly(Boolean);
        contactNumber.setReadOnly(Boolean);
        genderSelect.setReadOnly(Boolean);
    }
}
