package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

import java.time.LocalDate;
import java.time.ZoneId;



@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp1",layout = MainLayout.class)
public class PatientSignUp1 extends VerticalLayout {
    TextField firstName;
    TextField lastName;
    RadioButtonGroup<String> sex;
    EmailField emailField;
    TextField homeAddress;
    TextField postcode;
    TextField contactNumber;
    PasswordField password;
    PasswordField confirmPassword;
    DatePicker datePicker;



    public PatientSignUp1() {
        init();
        var formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                sex,
                datePicker,
                emailField,
                homeAddress, postcode,
                contactNumber,
                password, confirmPassword
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2)

        );
        formLayout.setColspan(sex, 2);
        formLayout.setColspan(datePicker, 1);
        formLayout.setColspan(emailField, 2);
        formLayout.setColspan(homeAddress, 2);
        formLayout.setColspan(contactNumber, 1);
        formLayout.setColspan(password, 1);
        formLayout.setColspan(confirmPassword, 1);

        Button submitButton = new Button("Next");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.add(new H1("Personal information"));
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("1000px");
        verticalLayout.setPadding(false);
        add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void init() {
        this.firstName = new TextField("First name");
        this.lastName = new TextField("Last name");
        this.sex = new RadioButtonGroup<>();
        sexSetUp();
        this.datePicker = new DatePicker("Date of birth");
        datePickerSetpUp();
        this.emailField = new EmailField("Email Address");
        emailFieldSetUp();
        this.homeAddress = new TextField("HomeAddress");
        this.postcode = new TextField("Post code");
        this.contactNumber = new TextField("Contact Number");
        contactNumberSetUp();
        this.password = new PasswordField("Password");
        passwordSetUp();
        this.confirmPassword = new PasswordField("Confirm password");
    }

    private void sexSetUp() {
        sex.setLabel("Sex");
        sex.setItems("Male","Female","Others","Prefer not to say");
    }

    private void contactNumberSetUp() {
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
    }

    private void passwordSetUp() {
        password.setLabel("Password");
        password.setRevealButtonVisible(false);
    }

    private void emailFieldSetUp() {
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    private void datePickerSetpUp() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now.minusYears(7));
        datePicker.setMin(now.minusYears(120));
        datePicker.setErrorMessage("Try another value");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
    }
}