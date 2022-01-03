package com.glucoclock.views;

import com.glucoclock.security.db.UserService;
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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Getpassword")
@Route(value = "forgotPassword")
public class getPassword extends HorizontalLayout {
    private UserService userService;
    EmailField emailField;
    PasswordField password;
    PasswordField confirmPassword;
    FormLayout formLayout;
    Button submitButton;
    VerticalLayout mainLayout;
    Button codeButton;
    TextField code;
    private H2 title = new H2("Reset password");
    private MenuBar menu = new MenuBar("NS");


    public getPassword(UserService userService){
        this.userService = userService;

        add(menu);
        init();
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        mainLayout.add(title);
        mainLayout.add(formLayout);
        mainLayout.add(submitButton);

       add(mainLayout);

    }





    private void init() {
        this.codeButton = new Button("send code");
        this.code = new TextField();
        code.setRequired(true);
        codeButton.addClickListener(e ->{
                    if(userService.getRepository().findByUsername(emailField.getValue())==null) {
                        Notification notification = Notification.show("Please sign up first");
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }else {
                        String code = verificationCode.getRandomNum();
                        String email = "Your code is: "+code;
                        VaadinSession.getCurrent().setAttribute("code",code);
                        SendMail.sendMail("Verification code",email,emailField.getValue());
                        Notification notification = Notification.show("You should receive an email by now. In case you dont "+email);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }
                }

        );

        mainLayoutSetUp();
        emailFieldSetUp();
        passwordSetUp();
        confirmPasswordSetUp();
        submitButtonSetUp();
        formLayoutSetUp();



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
                Notification notification = Notification.show("Check Emailfield");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if (!password.getValue().equals(confirmPassword.getValue())){
                Notification notification = Notification.show("Check Password");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else if(!VaadinSession.getCurrent().getAttribute("code").equals(code.getValue())){
                Notification notification = Notification.show("Wrong code");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                userService.updateUserPassword(emailField.getValue(),password.getValue());
                Notification notification = Notification.show("Updated!");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(HomeView.class)
                );
            }



        });
    }

    private void formLayoutSetUp() {
        formLayout = new FormLayout();
        formLayout.add(
                emailField,
                code,codeButton,
                password, confirmPassword

        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout.setColspan(emailField, 2);
        formLayout.setColspan(password, 2);
        formLayout.setColspan(confirmPassword, 2);
    }


    private void passwordSetUp() {
        password = new PasswordField("New password");
        password.setClearButtonVisible(true);
    }

    private void confirmPasswordSetUp() {
        confirmPassword = new PasswordField("Confirm password");
        confirmPassword.setClearButtonVisible(true);
    }

    private void emailFieldSetUp() {
        emailField = new EmailField("Email Address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
}
