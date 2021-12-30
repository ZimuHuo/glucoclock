package com.glucoclock.views.researcher;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.researchers_db.model.Researcher;
import com.glucoclock.database.researchers_db.service.ResearcherService;
import com.glucoclock.security.db.Authorities;
import com.glucoclock.security.db.AuthoritiesService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.doctor.DoctorSignUp1;
import com.glucoclock.views.doctor.DoctorStartView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
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


@PageTitle("Researcher Sign Up")
@Route(value = "researcher-sign-up-2")
public class ResearcherSignUp2 extends HorizontalLayout {
    private RadioButtonGroup<String> sex;
    private TextField apartmentAddress;
    private TextField streetAddress;
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

    //    Variables
    private final UserService userService;
    private final ResearcherService researcherService;
    private final AuthoritiesService authoritiesService;



    public ResearcherSignUp2(UserService userService, ResearcherService researcherService, AuthoritiesService authoritiesService) {
        this.userService = userService;
        this.researcherService = researcherService;
        this.authoritiesService = authoritiesService;

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
        add(verticalLayout);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

    }

    private void formlayout3SetUp() {
        this.formLayout3 = new FormLayout();
        formLayout3.add(
                apartmentAddress, streetAddress,
                postcode,city
        );
        formLayout2.setColspan(apartmentAddress, 2);
        formLayout2.setColspan(streetAddress, 2);
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
        this.apartmentAddress = new TextField("Address Line 1");
        this.streetAddress = new TextField("Addess Line 2");
        this.postcode = new TextField("Post code");
        this.city = new TextField("City");
        this.contactNumber = new TextField("Contact Number");
        contactNumberSetUp();
        datePicker.setClearButtonVisible(true);
        apartmentAddress.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("HomeAddressL1")!= null){
            apartmentAddress.setValue((String)VaadinSession.getCurrent().getAttribute("HomeAddressL1"));
        }
        streetAddress.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("HomeAddressL2")!= null){
            streetAddress.setValue((String)VaadinSession.getCurrent().getAttribute("HomeAddressL2"));
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
        apartmentAddress.setRequired(true);
        streetAddress.setRequired(true);
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
        submitButton = new Button("Sign Up");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left","auto");
        submitButton.addClickListener(e -> {


            if (sex.isEmpty()) {
                Notification notification = Notification.show("Check Sex Field");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (apartmentAddress.isEmpty()) {
                Notification notification = Notification.show("Check apartment address");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }if (streetAddress.isEmpty()) {
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

//                Create and save a new user
                User user = new User(
                        (String)VaadinSession.getCurrent().getAttribute("Email"),
                        (String)VaadinSession.getCurrent().getAttribute("Password"),
                        "RESEARCHER",
                        (byte) 1
                        //Role.RESEARCHER,
                );
                userService.getRepository().save(user);

//                Create and save a new researcher
                Researcher researcher = new Researcher(
                        (String)VaadinSession.getCurrent().getAttribute("FirstName"),
                        (String)VaadinSession.getCurrent().getAttribute("LastName"),
                        (String)VaadinSession.getCurrent().getAttribute("Email"),
                        (String)VaadinSession.getCurrent().getAttribute("HomeAddressL1"),
                        (String)VaadinSession.getCurrent().getAttribute("HomeAddressL2"),
                        (String)VaadinSession.getCurrent().getAttribute("Postcode"),
                        (String)VaadinSession.getCurrent().getAttribute("City"),
                        (String)VaadinSession.getCurrent().getAttribute("ContactNumber"),
                        (String)VaadinSession.getCurrent().getAttribute("Sex"),
                        (LocalDate) VaadinSession.getCurrent().getAttribute("Date"),
                        (String)VaadinSession.getCurrent().getAttribute("Institution"),
                        userService.getRepository().findByUsername((String)VaadinSession.getCurrent().getAttribute("Email")).getUid()
                );
                researcherService.getRepository().save(researcher);

//                Set the authority of the user as researcher
                Authorities authorities = new Authorities((String)VaadinSession.getCurrent().getAttribute("Email"),"RESEARCHER");
                authoritiesService.getRepository().save(authorities);

                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(ResearcherStart.class)
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
                    ui.navigate(ResearcherSignUp1.class)
            );

        });
    }

    private void setSession() {
        VaadinSession.getCurrent().setAttribute( "Sex",sex.getValue());
        VaadinSession.getCurrent().setAttribute( "HomeAddressL1",apartmentAddress.getValue());
        VaadinSession.getCurrent().setAttribute( "HomeAddressL2",streetAddress.getValue());
        VaadinSession.getCurrent().setAttribute( "Postcode",postcode.getValue());
        VaadinSession.getCurrent().setAttribute( "City",city.getValue());
        VaadinSession.getCurrent().setAttribute( "ContactNumber",contactNumber.getValue());
        VaadinSession.getCurrent().setAttribute( "Date",datePicker.getValue());
    }


}