package com.glucoclock.views.patient;

import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

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

@PageTitle("Patient Sign Up")
@Route(value = "patient-sign-up-2")
public class PatientSignUp2View extends Div {
    private RadioButtonGroup<String> sex;
    private TextField AddressL1;
    private TextField AddressL2;
    private TextField postcode;
    private TextField city;
    private TextField contactNumber;
    private DatePicker datePicker;
    private FormLayout formLayout1;
    private FormLayout formLayout2;
    private FormLayout formLayout3;
    private Button submitButton, previousButton;
    private H2 title = new H2("Personal information");
    private MenuBar menu = new MenuBar("NS");



    public PatientSignUp2View() {
        add(menu);
        init();
        formlayout1SetUp();
        formlayout2SetUp();
        formlayout3SetUp();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        verticalLayout.add(title);
        verticalLayout.add(formLayout1);
        verticalLayout.add(formLayout2);
        verticalLayout.add(formLayout3);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth(verticalLayout.getWidth());
        horizontalLayout.add(previousButton);
        horizontalLayout.add(submitButton);
        verticalLayout.add(horizontalLayout);
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(verticalLayout);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(hl);
    }

    private void formlayout3SetUp() {
        this.formLayout3 = new FormLayout();
        formLayout3.add(
                AddressL1, AddressL2,
                postcode,city
        );
        formLayout2.setColspan(AddressL1, 2);
        formLayout2.setColspan(AddressL2, 2);
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
        this.AddressL1 = new TextField("Address Line 1");
        this.AddressL2 = new TextField("Address Line 2");
        this.postcode = new TextField("Post code");
        this.city = new TextField("City");
        this.contactNumber = new TextField("Contact Number");
        contactNumberSetUp();
        datePicker.setClearButtonVisible(true);
        AddressL1.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("HomeAddressL1")!= null){
            AddressL1.setValue((String)VaadinSession.getCurrent().getAttribute("HomeAddressL1"));
        }
        AddressL2.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("HomeAddressL2")!= null){
            AddressL2.setValue((String)VaadinSession.getCurrent().getAttribute("HomeAddressL2"));
        }
        postcode.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("Postcode")!= null){
            postcode.setValue((String)VaadinSession.getCurrent().getAttribute("Postcode"));
        }
        if (VaadinSession.getCurrent().getAttribute("City")!= null){
            city.setValue((String)VaadinSession.getCurrent().getAttribute("City"));
        }


        submitButtonInit();
        previousButtonInit();
        sex.setRequired(true);
        datePicker.setRequired(true);
        AddressL1.setRequired(true);
        AddressL2.setRequired(true);
        postcode.setRequired(true);
        contactNumber.setRequired(true);
    }

    private void sexSetUp() {
        sex.setLabel("Gender");
        sex.setItems("Female","Male","Other","Prefer not to say");
        if (VaadinSession.getCurrent().getAttribute("Sex")!= null){
            sex.setValue((String)VaadinSession.getCurrent().getAttribute("Sex"));
        }
    }

    private void contactNumberSetUp() {
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
        if (VaadinSession.getCurrent().getAttribute("ContactNumber")!= null){
            contactNumber.setValue((String)VaadinSession.getCurrent().getAttribute("ContactNumber"));
        }
    }
    private void datePickerSetUp() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now.minusYears(7));
        datePicker.setMin(now.minusYears(120));
        datePicker.setErrorMessage("Try another value");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
        if (VaadinSession.getCurrent().getAttribute("Date")!= null){
            datePicker.setValue((LocalDate)VaadinSession.getCurrent().getAttribute("Date"));
        }
    }

    private void submitButtonInit() {
        submitButton = new Button("Next", new Icon(VaadinIcon.ARROW_RIGHT));
        submitButton.setIconAfterText(true);
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left","auto");
        submitButton.addClickListener(e -> {



            if (sex.isEmpty()) {
                Notification notification = Notification.show("Check Sex Field");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (AddressL1.isEmpty()) {
                Notification notification = Notification.show("Check apartment address");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (AddressL2.isEmpty()) {
                Notification notification = Notification.show("Check street address");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (postcode.isEmpty()) {
                Notification notification = Notification.show("Check post code");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (city.isEmpty()) {
                Notification notification = Notification.show("Check city");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (contactNumber.isEmpty()) {
                Notification notification = Notification.show("Check contact number");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (datePicker.isEmpty()) {
                Notification notification = Notification.show("Check date");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else {

                setSession();
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientSignUp3View.class)
                );
            }



        });
    }

    private void previousButtonInit() {
        previousButton = new Button("Previous", new Icon(VaadinIcon.ARROW_LEFT));
        previousButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousButton.getElement().getStyle().set("margin-right", "auto");
        previousButton.addClickListener(e -> {
            setSession();
            previousButton.getUI().ifPresent(ui ->
                    ui.navigate(PatientSignUp1View.class)
            );




        });
    }

    private void setSession() {
        VaadinSession.getCurrent().setAttribute( "Sex",sex.getValue());
        VaadinSession.getCurrent().setAttribute( "HomeAddressL1", AddressL1.getValue());
        VaadinSession.getCurrent().setAttribute( "HomeAddressL2", AddressL2.getValue());
        VaadinSession.getCurrent().setAttribute( "Postcode",postcode.getValue());
        VaadinSession.getCurrent().setAttribute( "City",city.getValue());
        VaadinSession.getCurrent().setAttribute( "ContactNumber",contactNumber.getValue());
        VaadinSession.getCurrent().setAttribute( "Date",datePicker.getValue());
    }

}
