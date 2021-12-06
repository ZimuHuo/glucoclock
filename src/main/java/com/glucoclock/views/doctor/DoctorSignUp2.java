package com.glucoclock.views.doctor;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;


@PageTitle("Sign up your doctor account")
@Route(value = "DoctorSignUp2",layout = MainLayout.class)
public class DoctorSignUp2 extends HorizontalLayout {
    private RadioButtonGroup<String> sex;
    private TextField apartmentAddress;
    private TextField streetAddress;
    private TextField postcode;
    private TextField contactNumber;
    private DatePicker datePicker;
    private FormLayout formLayout1;
    private FormLayout formLayout2;
    private FormLayout formLayout3;
    private VerticalLayout mainLayout;
    Button previousButton, submitButton;
    HorizontalLayout Buttons;



    public DoctorSignUp2() {
        init();

        mainLayout.add(new H1("Personal information"));
        mainLayout.add(formLayout1);
        mainLayout.add(formLayout2);
        mainLayout.add(formLayout3);
        mainLayout.add(Buttons);

        this.add(mainLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
    }



    private void init() {
        mainLayoutSetUp();
        previousButtonSetUp();
        submitButtonSetUp();
        sexSetUp();
        datePickerSetUp();
        apartmentAddressSetUp();
        streetAddressSetUp();
        postcodeSetUp();
        contactNumberSetUp();
        formlayout1SetUp();
        formlayout2SetUp();
        formlayout3SetUp();
        ButtonsSetUp();
    }

    private void ButtonsSetUp() {
        Buttons = new HorizontalLayout();
        Buttons.setWidth(mainLayout.getWidth());
        Buttons.add(previousButton);
        Buttons.add(submitButton);
    }

    private void mainLayoutSetUp() {
        mainLayout = new VerticalLayout();
        mainLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(false);
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

    private void submitButtonSetUp() {
        submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left","auto");
    }

    private void previousButtonSetUp() {
        previousButton = new Button("Previous", new Icon(VaadinIcon.ARROW_LEFT));
        previousButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousButton.getElement().getStyle().set("margin-right", "auto");
        previousButton.addClickListener(e ->
                previousButton.getUI().ifPresent(ui ->
                        ui.navigate(DoctorSignUp1.class)
                )
        );
    }

    private void postcodeSetUp() {
        postcode = new TextField("Post code");
        postcode.setClearButtonVisible(true);
    }

    private void streetAddressSetUp() {
        streetAddress = new TextField("Street address");
        streetAddress.setClearButtonVisible(true);
    }

    private void apartmentAddressSetUp() {
        apartmentAddress = new TextField("Apartment address");
        apartmentAddress.setClearButtonVisible(true);
    }

    private void sexSetUp() {
        sex = new RadioButtonGroup<>();
        sex.setLabel("Gender");
        sex.setItems("Male","Female","Others","Prefer not to say");
    }

    private void contactNumberSetUp() {
        contactNumber = new TextField("Contact Number");
        contactNumber.setClearButtonVisible(true);
        contactNumber.setHelperText("Include country and area prefixes");
    }

    private void datePickerSetUp() {
        datePicker = new DatePicker("Date of birth");
        datePicker.setClearButtonVisible(true);
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now.minusYears(7));
        datePicker.setMin(now.minusYears(120));
        datePicker.setErrorMessage("Try another value");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
    }
}