package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;



@PageTitle("Sign up your patient account")
@Route(value = "PatientSetting1",layout = MainLayout.class)
public class PatientSetting1 extends HorizontalLayout {
    //     These are temporary variables
    //     Should be got from database
    //    --------------------------------------------
    String FName = "Zhao";
    String LName = "Gao";
    String Email = "zg919@ic.ac.uk";
    String Home = "Flat8, 95 Queens Gate";
    String PostCode = "SW7 5AB";
    String Phone = "44 07421471833";
    LocalDate Birth = LocalDate.of(2001,6,13);
    String Gender = "Male";
    String Diabetes = "Type I";
    //    --------------------------------------------

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

    VerticalLayout Mid = new VerticalLayout();




    public PatientSetting1() {
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

        Mid.add(
                formLayout,
                postcode,
                diabetesSelect,
                insulinSelect
        );

        Mid.setMaxWidth("600px");
        Mid.setPadding(false);

        setJustifyContentMode(JustifyContentMode.CENTER);

    };



    private void init() {
//      Initialize the components
        add(Mid);

//        First name
        this.firstName = new TextField("First name");
        firstName.setValue(FName);

//        Last Name
        this.lastName = new TextField("Last name");
        lastName.setValue(LName);

//        Birth Date
        this.datePicker = new DatePicker("Date of birth");
        datePickerSetUp();
        datePicker.setValue(Birth);

//        Email
        this.emailField = new EmailField();
        emailFieldSetUp();
        emailField.setValue(Email);

//        Home address
        this.homeAddress = new TextField("Home address");
        homeAddress.setValue(Home);

//        Postcode
        this.postcode = new TextField("Postcode");
        postcode.setValue(PostCode);

//        Phone number
        this.contactNumber = new TextField();
        contactNumberSetUp();
        contactNumber.setValue(Phone);

//        Gender
        this.genderSelect = new Select<>("Male","Female","Others","Prefer not to say");
        genderSelect.setValue(Gender);
        genderSelect.setLabel("Gender");

//        Diabetes type
        this.diabetesSelect = new Select<>("Type I","Type II");
        diabetesSelect.setValue(Diabetes);
        diabetesSelect.setLabel("Type of diabetes");

//        Insulin type
        this.insulinSelect = new CheckboxGroup<>();
        insulinSelect.setLabel("Insulin type");
        insulinSelect.setItems("Rapid-acting insulin","Short-acting insulin","Intermediate-acting insulin","Long-acting insulin");
    }

    private void contactNumberSetUp() {
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
    }



    private void emailFieldSetUp() {
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    }

    private void datePickerSetUp() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now);
        datePicker.setMin(now.minusYears(120));
        datePicker.setHelperText("You must be a minimum of 7 years old");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
    }
}