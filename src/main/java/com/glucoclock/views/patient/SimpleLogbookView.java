package com.glucoclock.views.patient;

import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.util.SendMail;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.UUID;

@PageTitle("Add Simple Logbook Entry")
@Route(value = "patient/addsimplelogbookentry")
public class SimpleLogbookView extends Div {
    private H3 title = new H3("Add Simple Logbook Entry");
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private NumberField bloodGlucose;
    private NumberField carbohydrate;
    private Button submitButton = new Button("Upload");
    private MenuBar menu = new MenuBar("PNS");

    private Integer time;

    private UUID patientUid;

    private final UserService userService;
    private final SimpleLogBookService simpleLogBookService;
    private final NotificationService notificationService;
    private final PatientService patientService;
    private final DoctorPatientService doctorPatientService;
    private final LogService logService;
    private final ComprehensiveLogBookService comprehensiveLogBookService;
    private final DoctorService doctorService;

    public SimpleLogbookView(UserService userService, SimpleLogBookService simpleLogBookService, NotificationService notificationService, PatientService patientService, DoctorPatientService doctorPatientService, LogService logService, ComprehensiveLogBookService comprehensiveLogBookService, DoctorService doctorService){

        this.userService = userService;
        this.simpleLogBookService = simpleLogBookService;
        this.notificationService = notificationService;
        this.patientService = patientService;
        this.doctorPatientService = doctorPatientService;
        this.logService = logService;
        this.comprehensiveLogBookService = comprehensiveLogBookService;
        this.doctorService = doctorService;

        //get patient uid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        String userName=authentication.getName();
        System.out.print(authentication.getName()); // here it gets the username. absolutely fantastic
        User user= this.userService.getRepository().findByUsername(userName); //return user
        patientUid=user.getUid();   //get patient uid

        init();
        add(menu);
        //add(menuBar());
        add(createFields());
    }

    private void init() {
        prepost = new ComboBox<>("Pre/Post");
        meal = new ComboBox<>("Meal");
        bloodGlucose = new NumberField("Blood Glucose");
        carbohydrate = new NumberField("Carbohydrate");
        Div bloodGlucoseUnit = new Div();
        bloodGlucoseUnit.setText("mmol/L");
        bloodGlucose.setSuffixComponent(bloodGlucoseUnit);
        Div carbsUnit = new Div();
        carbsUnit.setText("g");
        carbohydrate.setSuffixComponent(carbsUnit);
        prepost.setItems("Pre","Post");
        meal.setItems("Breakfast","Lunch","Dinner");

        prepost.addValueChangeListener(e->{
            if(!prepost.isEmpty()&& !meal.isEmpty()){
                Integer time = 0;
                String TimeString = prepost.getValue()+meal.getValue();
                if (TimeString.equals("PreBreakfast")) time =1;
                else if (TimeString.equals("PostBreakfast")) time =2;
                else if (TimeString.equals("PreLunch")) time =3;
                else if (TimeString.equals("PostLunch")) time =4;
                else if (TimeString.equals("PreDinner")) time =5;
                else if (TimeString.equals("PostDinner")) time =6;

                if (simpleLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                    com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("You already entered value for this entry. You will override past data");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
        meal.addValueChangeListener(e->{
            if(!prepost.isEmpty()&& !meal.isEmpty()){
                time = 0;
                String TimeString = prepost.getValue()+meal.getValue();
                if (TimeString.equals("PreBreakfast")) time =1;
                else if (TimeString.equals("PostBreakfast")) time =2;
                else if (TimeString.equals("PreLunch")) time =3;
                else if (TimeString.equals("PostLunch")) time =4;
                else if (TimeString.equals("PreDinner")) time =5;
                else if (TimeString.equals("PostDinner")) time =6;

                if (simpleLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                    com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("You already entered value for this entry. You will override past data");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
    }


    private Component createFields(){
        var formLayout = new FormLayout();
        formLayout.add(
                prepost, meal,
                bloodGlucose,
                carbohydrate
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );

        formLayout.setColspan(bloodGlucose, 2);
        formLayout.setColspan(carbohydrate,2 );
        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    //Submit Button
        submitButton.addClickListener(e -> {
                    //check is there a data at same date and time
                    SimpleLogBook simple = simpleLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid, time, (LocalDate) VaadinSession.getCurrent().getAttribute("date"));
                        if (bloodGlucose.isEmpty()) {
                            com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("Your glucose level is empty");
                            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        } else if (prepost.isEmpty()) {
                            com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("Please select your time correctly");
                            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        } else if (meal.isEmpty()) {
                            com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("Please select your time correctly");
                            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        } else{
                            //if there is no data
                            //Notification
                            //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                            if (bloodGlucose.getValue() > 140) {
                                //if patient do not have a doctor don't send email
                                if (doctorPatientService.checkPatient(patientUid)) {
                                    Notifications n = new Notifications(
                                            patientService,
                                            patientUid,
                                            doctorPatientService.getRepository().getDoctorPatientByPatientuid(patientUid).getDoctoruid(), // Doctor uid
                                            "Blood Glucose Alarm"
                                    );
                    SendMail sendMail = new SendMail();
                    String doctorEmail = doctorService.getRepository().getDoctorByUid(doctorPatientService.getRepository().getDoctorPatientByPatientuid(patientUid).getDoctoruid()).getEmail();
                    sendMail.sendMail("Act now",n.getPatientFirstName() + " " + n.getPatientLastName() + " is experiencing abnormal blood glucose levels.\n" +
                            "\n" +
                            "Date: " + n.getDate().toLocalDate() + "\n" +
                            "Time: " + n.getDate().toLocalTime() + "\n" +
                            "Blood glucose level: " + bloodGlucose.getValue() + " mmol/L.",doctorEmail);

//                        Create and save a new notification

                                    n.setShortMessage("Blood glucose level " + bloodGlucose.getValue() + " units");
                                    n.setCompleteMessage(
                                            n.getPatientFirstName() + " " + n.getPatientLastName() + " is experiencing abnormal blood glucose levels.\n" +
                                                    "\n" +
                                                    "Date: " + n.getDate().toLocalDate() + "\n" +
                                                    "Time: " + n.getDate().toLocalTime() + "\n" +
                                                    "Blood glucose level: " + bloodGlucose.getValue() + " mmol/L."
                                    );
                                    notificationService.getRepository().save(n);
                                }

                            }

                            if(simple == null) {
                                //no data entered before
                                //Create a new simple logbook
                                SimpleLogBook simpleLogBook = new SimpleLogBook(
                                        patientUid,
                                        (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                                        prepost.getValue() + meal.getValue(),
                                        bloodGlucose.getValue().toString(),
                                        carbohydrate.getValue().toString()
                                );

                                //Save the simple logbook
                                simpleLogBookService.getRepository().save(simpleLogBook);
                                if (logService.findLogBookbyDate((LocalDate) VaadinSession.getCurrent().getAttribute("date"), patientUid) == null) {
                                    Log log = new Log(patientUid, (LocalDate) VaadinSession.getCurrent().getAttribute("date"), 1);
                                    logService.getRepository().save(log);
                                }
                            }
                            else{
                                //already have data, need to replace
                                simpleLogBookService.updateBloodGlucose(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,bloodGlucose.getValue().toString());
                                simpleLogBookService.updateCarbIntake(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,carbohydrate.getValue().toString());
                            }

                                //Navigation
                                submitButton.getUI().ifPresent(ui ->
                                        ui.navigate(ConfirmationView.class)
                                );
                        }
                }
        );
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return horizontalLayout;

    }
}