package com.glucoclock.views.researcher;

import com.glucoclock.security.db.UserService;
import com.glucoclock.views.components.MenuBar;
import com.glucoclock.views.util.SendMail;
import com.glucoclock.views.util.verificationCode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

/*
This page is used for researcher sign up part 1.
It should store the essential information of the researcher details.
 */
@PageTitle("Researcher Sign Up")
@Route(value = "researcher-sign-up-1")
public class ResearcherSignUp1View extends HorizontalLayout {
    private TextField firstName;
    private TextField lastName;
    private EmailField emailField;
    private PasswordField password;
    private PasswordField confirmPassword;
    private FormLayout formLayout;
    private Button submitButton;
    private Select<String> institution;
    private VerticalLayout mainLayout;
    private MenuBar menu = new MenuBar("NS");
    private UserService userService;
    private Button codeButton;
    private TextField code;
    public ResearcherSignUp1View(UserService userService) {
        this.userService = userService;
        add(menu);
        this.codeButton = new Button("send code");
        this.code = new TextField();
        /*
         this creates a simple text field and button for getting and sending verification codes
         */
        code.setRequired(true);
        codeButton.addClickListener(e ->{
                    if(userService.getRepository().findByUsername(emailField.getValue())!=null) {
                        Notification notification = Notification.show("Please choose another email address");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }else {
                        String code = verificationCode.getRandomNum();
                        String email = "Your one time verification code is: "+code;
                        VaadinSession.getCurrent().setAttribute("code",code);
                        SendMail.sendMail("Verification code",email,emailField.getValue());
                        Notification notification = Notification.show("You should receive an email by now. For testing purposes, here's the code: "+email);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                    }
                }
        );
        init();
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        mainLayout.add(new H2("     "));
        mainLayout.add(new H2("Set up your account"));
        mainLayout.add(formLayout);
        mainLayout.add(submitButton);

        add(mainLayout);
    }

    /*
          this sets up the submit button action listener. It checks all the input in the current page and store that in the user session
          */
    private void submitButtonSetUp() {
        this.submitButton = new Button("Next", new Icon(VaadinIcon.ARROW_RIGHT));
        submitButton.setIconAfterText(true);
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");

        submitButton.addClickListener(e -> {
            if(firstName.isEmpty() || lastName.isEmpty() || emailField.isEmpty() || emailField.isInvalid() || password.isEmpty() || !password.getValue().equals(confirmPassword.getValue()) || institution.isEmpty()) {
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
                    Notification notification = Notification.show("A valid email address is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (password.isEmpty()){
                    Notification notification = Notification.show("Password is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (!password.getValue().equals(confirmPassword.getValue())){
                    Notification notification = Notification.show("You have entered different password", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (institution.isEmpty()){
                    Notification notification = Notification.show("Institution is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                if(password.isInvalid()){
                    Notification notification = Notification.show("Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters", 10000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            } else if(userService.getRepository().findByUsername(emailField.getValue())!=null){
                Notification notification = Notification.show("This email address is already used. Sign in directly or choose another email address.", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if(!VaadinSession.getCurrent().getAttribute("code").equals(code.getValue())){
                Notification notification = Notification.show("Wrong code", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else {
                //Save current information and move to next page
                VaadinSession.getCurrent().setAttribute( "FirstName",firstName.getValue());
                VaadinSession.getCurrent().setAttribute( "LastName",lastName.getValue());
                VaadinSession.getCurrent().setAttribute( "Email",emailField.getValue());
                VaadinSession.getCurrent().setAttribute( "Password",password.getValue());
                VaadinSession.getCurrent().setAttribute("Institution",institution.getValue());

                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(ResearcherSignUp2View.class)
                );
            }
        });
    }
/*
layout of the form
 */
    private void formLayoutSetUp() {
        this.formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                emailField,
                code, codeButton,
                institution,
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
        formLayout.setColspan(institution, 2);
        formLayout.setColspan(password, 1);
        formLayout.setColspan(confirmPassword, 1);
    }

    private void init() {
        mainLayoutSetUp();
        firstNameSetUp();
        lastNameSetUp();
        emailFieldSetUp();
        confirmPasswordSetUp();
        passwordSetUp();
        submitButtonSetUp();
        institutionSetUp();
        formLayoutSetUp();
    }

    private void lastNameSetUp() {
        lastName = new TextField("Last name");
        lastName.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("LastName")!= null){
            lastName.setValue((String)VaadinSession.getCurrent().getAttribute("LastName"));
        }
    }

    private void firstNameSetUp() {
        firstName = new TextField("First name");
        firstName.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("FirstName")!= null){
            firstName.setValue((String)VaadinSession.getCurrent().getAttribute("FirstName"));
        }
    }

    private void mainLayoutSetUp() {
        mainLayout = new VerticalLayout();
        mainLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(false);
    }

    private void passwordSetUp() {
        password = new PasswordField("Password");
        password.setLabel("Password");
        password.setClearButtonVisible(true);
        password.setPattern("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
        password.setErrorMessage("Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters");
        //Change the input format of 'confirmPassword' when user changes the input in 'password'
        password.addValueChangeListener(e ->
                confirmPassword.setPattern(password.getValue())
        );

        if (VaadinSession.getCurrent().getAttribute("Password")!= null){
            password.setValue((String)VaadinSession.getCurrent().getAttribute("Password"));
        }
    }

    private void confirmPasswordSetUp() {
        confirmPassword = new PasswordField("Confirm password");
        confirmPassword.setClearButtonVisible(true);
        confirmPassword.setErrorMessage("You must fill in the same password again");
        if (VaadinSession.getCurrent().getAttribute("Password")!= null){
            confirmPassword.setValue((String)VaadinSession.getCurrent().getAttribute("Password"));
        }
    }

    private void emailFieldSetUp() {
        emailField = new EmailField("Email Address");
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        if (VaadinSession.getCurrent().getAttribute("Email")!= null){
            emailField.setValue((String)VaadinSession.getCurrent().getAttribute("Email"));
        }
    }

    private void institutionSetUp() {
        institution = new Select<String>("Imperial College London");
        institution.setLabel("Institution");
        if (VaadinSession.getCurrent().getAttribute("Institution")!= null){
            institution.setValue((String)VaadinSession.getCurrent().getAttribute("Institution"));
        }
    }

}
