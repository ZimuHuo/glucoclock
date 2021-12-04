package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp1",layout = MainLayout.class)
public class PatientSignUp1 extends HorizontalLayout {
    private TextField firstName;
    private TextField lastName;
    private EmailField emailField;
    private PasswordField password;
    private PasswordField confirmPassword;
    private FormLayout formLayout;
    private Button submitButton;
    private VerticalLayout verticalLayout;

    public PatientSignUp1() {
        init();
        formLayoutSetUp();
        submitButtonSetUp();
        veticalLayoutSetUp();
        this.add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void veticalLayoutSetUp() {
        this.verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.add(new H1("Set up your account"));
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
    }

    private void submitButtonSetUp() {
        this.submitButton = new Button("Next");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
    }

    private void formLayoutSetUp() {
        this.formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                emailField,
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
        formLayout.setColspan(password, 1);
        formLayout.setColspan(confirmPassword, 1);
    }

    private void init() {
        this.firstName = new TextField("First name");
        this.lastName = new TextField("Last name");
        this.emailField = new EmailField("Email Address");
        this.password = new PasswordField("Password");
        this.confirmPassword = new PasswordField("Confirm password");
        passwordSetUp();
        emailFieldSetUp();
        setAlltoClearButtonVisible();
    }

    private void setAlltoClearButtonVisible() {
        firstName.setClearButtonVisible(true);
        lastName.setClearButtonVisible(true);
        password.setClearButtonVisible(true);
        confirmPassword.setClearButtonVisible(true);
        emailField.setClearButtonVisible(true);
    }


    private void passwordSetUp() {
        password.setLabel("Password");
    }

    private void emailFieldSetUp() {
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setPattern("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

}
