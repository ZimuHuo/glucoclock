package com.glucoclock.views.patient;

import com.glucoclock.security.db.UserService;
import com.glucoclock.views.components.MenuBar;
import com.glucoclock.views.util.SendMail;
import com.glucoclock.views.util.verificationCode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

/*
This page is used for patient sign up part 1.
It should store the essential information of the patient details.
 */

@PageTitle("Patient Sign Up")
@Route(value = "patient-sign-up-1")
public class PatientSignUp1View extends Div {
    TextField firstName;
    TextField lastName;
    EmailField emailField;
    PasswordField password;
    PasswordField confirmPassword;
    FormLayout formLayout;
    Button submitButton;
    VerticalLayout mainLayout;
    Button codeButton;
    TextField code;
    private H2 title = new H2("Set up your account");
    private MenuBar menu = new MenuBar("NS");
    private UserService userService;

    public PatientSignUp1View(UserService userService) {
        this.userService = userService;
        add(menu);
        init();
        HorizontalLayout hl = new HorizontalLayout();
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        mainLayout.add(title);
        mainLayout.add(formLayout);
        mainLayout.add(submitButton);

        hl.add(mainLayout);
        add(hl);
    }



    private void init() {
        getVerification();

        mainLayoutSetUp();
        firstNameSetUp();
        lastNameSetUp();
        emailFieldSetUp();
        confirmPasswordSetUp();
        passwordSetUp();
        submitButtonSetUp();
        formLayoutSetUp();



    }
    /*
    this creates a simple text field and button for getting and sending verification codes
     */
    private void getVerification() {
        this.codeButton = new Button("send code");
        this.code = new TextField();
        code.setRequired(true);
        codeButton.addClickListener(e ->{
            if(userService.getRepository().findByUsername(emailField.getValue())!=null) {
                Notification notification = Notification.show("This email address is already used. Sign in directly or choose another email address.");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else {
                String code = verificationCode.getRandomNum();
                String email = "Your one-time verification code is: "+code;
                VaadinSession.getCurrent().setAttribute("code",code);
                SendMail.sendMail("Verification code",email,emailField.getValue());
                Notification notification = Notification.show("You should receive an email by now. For testing purposes, here's the code: "+email);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        }
        );
    }

    private void mainLayoutSetUp() {
        mainLayout = new VerticalLayout();
        mainLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(false);
    }

    private void submitButtonSetUp() {
        submitButton = new Button("Next", new Icon(VaadinIcon.ARROW_RIGHT));
        submitButton.setIconAfterText(true);
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
        submitButton.addClickListener(e -> {
            if (emailField.isInvalid()) {
                Notification notification = Notification.show("A valid email is required", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if (!password.getValue().equals(confirmPassword.getValue())){
                Notification notification = Notification.show("Password is required", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if(password.isInvalid()){
                Notification notification = Notification.show("Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters", 10000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if(firstName.isEmpty()){
                Notification notification = Notification.show("First name is required", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if(lastName.isEmpty()){
                Notification notification = Notification.show("Last name is required", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if(userService.getRepository().findByUsername(emailField.getValue())!=null){
                Notification notification = Notification.show("This email address is already used. Sign in directly or choose another email address.", 5000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if(!VaadinSession.getCurrent().getAttribute("code").equals(code.getValue())){
                Notification notification = Notification.show("Wrong code", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                VaadinSession.getCurrent().setAttribute( "FirstName",firstName.getValue());
                VaadinSession.getCurrent().setAttribute( "LastName",lastName.getValue());
                VaadinSession.getCurrent().setAttribute( "Email",emailField.getValue());
                VaadinSession.getCurrent().setAttribute( "Password",password.getValue());
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientSignUp2View.class)
                );
            }



        });
    }

    private void formLayoutSetUp() {
        formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                emailField,
                code,codeButton,
                password, confirmPassword
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout.setColspan(firstName,1);
        formLayout.setColspan(lastName, 1);
        formLayout.setColspan(emailField, 2);
        formLayout.setColspan(password, 2);
        formLayout.setColspan(confirmPassword, 2);
    }

    private void firstNameSetUp() {
        firstName = new TextField("First name");
        firstName.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("FirstName")!= null){
            firstName.setValue((String)VaadinSession.getCurrent().getAttribute("FirstName"));
        }
    }

    private void lastNameSetUp() {
        lastName = new TextField("Last name");
        lastName.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("LastName")!= null){
            lastName.setValue((String)VaadinSession.getCurrent().getAttribute("LastName"));
        }
    }

    private void passwordSetUp() {
        password = new PasswordField("Password");
        //remove the special character requirement
        password.setPattern("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
        password.setErrorMessage("Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters");
        password.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("Password")!= null){
            password.setValue((String)VaadinSession.getCurrent().getAttribute("Password"));
        }
        //Change the input format of 'confirmPassword' when user changes the input in 'password'
        password.addValueChangeListener(e ->
                confirmPassword.setPattern(password.getValue())
        );
    }

    private void confirmPasswordSetUp() {
        confirmPassword = new PasswordField("Confirm password");
        confirmPassword.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("Password")!= null){
            confirmPassword.setValue((String)VaadinSession.getCurrent().getAttribute("Password"));
        }
    }

    private void emailFieldSetUp() {
        emailField = new EmailField("Email Address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        if (VaadinSession.getCurrent().getAttribute("Email")!= null){
            emailField.setValue((String)VaadinSession.getCurrent().getAttribute("Email"));
        }
    }
}
