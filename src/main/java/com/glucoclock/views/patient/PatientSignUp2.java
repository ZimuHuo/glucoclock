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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;

/*
for(Component c : myLayout) {
    if(c instanceof Field) {
        Field f = (Field) c;
        doSomethingWith(f.getValue());
    }
}
 */

@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp2",layout = MainLayout.class)
public class PatientSignUp2 extends HorizontalLayout {
    private RadioButtonGroup<String> sex;
    private TextField apartmentAddress;
    private TextField streetAddress;
    private TextField postcode;
    private TextField contactNumber;
    private DatePicker datePicker;
    private FormLayout formLayout1;
    private FormLayout formLayout2;
    private FormLayout formLayout3;
    private Button previousButton;
    private Button submitButton;
    private VerticalLayout verticalLayout;
    private HorizontalLayout horizontalLayout;



    public PatientSignUp2() {
        init();
        formlayout1SetUp();
        formlayout2SetUp();
        formlayout3SetUp();
        previousButtonSetUp();
        submitButtonSetUp();
        verticalLayoutSetUp();
        horizontalLayoutSetUp();
        this.add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void horizontalLayoutSetUp() {
        this.horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth(verticalLayout.getWidth());
        horizontalLayout.add(previousButton);
        horizontalLayout.add(submitButton);
    }

    private void verticalLayoutSetUp() {
        this.verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.add(new H1("Personal information"));
        verticalLayout.add(formLayout1);
        verticalLayout.add(formLayout2);
        verticalLayout.add(formLayout3);
        verticalLayout.add(horizontalLayout);
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
    }

    private void submitButtonSetUp() {
        this.submitButton = new Button("Next");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left","auto");
    }

    private void previousButtonSetUp() {
        this.previousButton = new Button("Previous");
        previousButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousButton.getElement().getStyle().set("margin-right", "auto");
    }

    private void formlayout3SetUp() {
        this.formLayout3 = new FormLayout();
        formLayout3.add(
                apartmentAddress, streetAddress,
                postcode
        );
        formLayout3.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
    }

    private void formlayout2SetUp() {
        this.formLayout2 = new FormLayout();
        formLayout2.add(
                contactNumber
        );
        formLayout2.setColspan(contactNumber, 1);
        formLayout2.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout2.setColspan(postcode, 1);
    }

    private void formlayout1SetUp() {
        this.formLayout1 = new FormLayout();
        formLayout1.add(
                sex,
                datePicker
        );
        formLayout1.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout1.setColspan(sex, 2);
        formLayout1.setColspan(datePicker, 1);
    }

    private void init() {
        this.sex = new RadioButtonGroup<>();
        sexSetUp();
        this.datePicker = new DatePicker("Date of birth");
        datePickerSetUp();
        this.apartmentAddress = new TextField("Apartment address");
        this.streetAddress = new TextField("Street address");
        this.postcode = new TextField("Post code");
        this.contactNumber = new TextField("Contact Number");
        contactNumberSetUp();
        datePicker.setClearButtonVisible(true);
        apartmentAddress.setClearButtonVisible(true);
        streetAddress.setClearButtonVisible(true);
        postcode.setClearButtonVisible(true);
        contactNumber.setClearButtonVisible(true);
        sex.setRequired(true);
        datePicker.setRequired(true);
        apartmentAddress.setRequired(true);
        streetAddress.setRequired(true);
        postcode.setRequired(true);
        contactNumber.setRequired(true);
    }

    private void sexSetUp() {
        sex.setLabel("Gender");
        sex.setItems("Male","Female","Others","Prefer not to say");
    }

    private void contactNumberSetUp() {
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
    }
    private void datePickerSetUp() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now.minusYears(7));
        datePicker.setMin(now.minusYears(120));
        datePicker.setErrorMessage("Try another value");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
    }


}
