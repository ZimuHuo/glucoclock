package com.glucoclock.views.patient;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
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

@PageTitle("Add Comprehensive Logbook Entry")
@Route(value = "patient/add-comprehensive-logbook-entry")
public class ComprehensiveLogbookView extends Div {
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private NumberField bloodGlucose;
    private NumberField carbohydrate;
    private NumberField insulinDose;
    private Button test1 = new Button("Test"); //Menubar test button
    private Button test2 = new Button("Test");
    private H3 title = new H3("Add Comprehensive Logbook Entry");
    private MenuBar menu = new MenuBar("PNS");

    private UUID patientUid;

    private final UserService userService;
    private final ComprehensiveLogBookService comprehensiveLogBookService;
    private final NotificationService notificationService;
    private final PatientService patientService;
    private final DoctorPatientService doctorPatientService;
    private final LogService logService;
    private final SimpleLogBookService simpleLogBookService;

    public ComprehensiveLogbookView(UserService userService, ComprehensiveLogBookService comprehensiveLogBookService, NotificationService notificationService, PatientService patientService, DoctorPatientService doctorPatientService, LogService logService, SimpleLogBookService simpleLogBookService){
        this.userService = userService;
        this.comprehensiveLogBookService = comprehensiveLogBookService;
        this.notificationService = notificationService;
        this.patientService = patientService;
        this.doctorPatientService = doctorPatientService;
        this.logService = logService;
        this.simpleLogBookService = simpleLogBookService;

        //get patient uid
        //save to database
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        String userName=authentication.getName();
        System.out.print(authentication.getName()); // here it gets the username. absolutely fantastic
        User user= this.userService.getRepository().findByUsername(userName); //return user
        patientUid=user.getUid();   //get patient uid

        init();
        add(menu);
        var formLayout = new FormLayout();
        formLayout.add(
                prepost, meal,
                bloodGlucose,
                carbohydrate,
                insulinDose
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );

        formLayout.setColspan(bloodGlucose, 2);
        formLayout.setColspan(carbohydrate,2);
        formLayout.setColspan(insulinDose,2);
        formLayout.setMaxWidth("40%");
        Button submitButton = new Button("Upload");
        submitButton.setWidth("12%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e -> {
                    if (bloodGlucose.isEmpty()) {
                        Notification notification = Notification.show("Your glucose level is empty");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (prepost.isEmpty()) {
                        Notification notification = Notification.show("Please select your time correctly");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (meal.isEmpty()) {
                        Notification notification = Notification.show("Please select your time correctly");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else {
                        //check input validity
                        Double bg = bloodGlucose.getValue();
                        //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                        if (bg > 140) {
                            Notification.show("Abnormal Blood Glucose Level").addThemeVariants(NotificationVariant.LUMO_ERROR);//change to save to notification db later

                            //if patient do not have a doctor don't send email
                            if(doctorPatientService.checkPatient(patientUid)) {
//                    SendMail sendMail = new SendMail();
//                    sendMail.sendMail("Act now","Glucose is high","Zimuhuo@outlook.com");


                                Notifications n = new Notifications(
                                        patientService,
                                        patientUid,
                                        doctorPatientService.getRepository().getDoctorPatientByPatientuid(patientUid).getDoctoruid(), // Doctor uid
                                        "Blood Glucose Alarm"
                                );
                                n.setShortMessage("Blood glucose level " + bloodGlucose.getValue() + " units");
                                n.setCompleteMessage(
                                        n.getPatientFirstName() + " " + n.getPatientLastName() + " is experiencing abnormal blood glucose levels.\n" +
                                                "\n" +
                                                "Date: " + n.getDate().toLocalDate() + "\n" +
                                                "Time: " + n.getDate().toLocalTime() + "\n" +
                                                "Blood glucose level: " + bloodGlucose.getValue() + " units."
                                );
                                notificationService.getRepository().save(n);
                            }
                        }

                        ComprehensiveLogBook comprehensiveLogBook = new ComprehensiveLogBook(
                                patientUid,
                                (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                                prepost.getValue() + meal.getValue(),
                                bloodGlucose.getValue().toString(),
                                carbohydrate.getValue().toString(),
                                insulinDose.getValue().toString()

                        );
//                        String TimeString = prepost.getValue() + meal.getValue();
//                        int time = 0;
//                        if (TimeString.equals("PreBreakfast")) time = 1;
//                        else if (TimeString.equals("PostBreakfast")) time = 2;
//                        else if (TimeString.equals("PreLunch")) time = 3;
//                        else if (TimeString.equals("PostLunch")) time = 4;
//                        else if (TimeString.equals("PreDinner")) time = 5;
//                        else if (TimeString.equals("PostDinner")) time = 6;
                        comprehensiveLogBookService.getRepository().save(comprehensiveLogBook);

                        //save log in logdb if no logbook entered in this date
                        if(logService.findLogBookbyDate((LocalDate) VaadinSession.getCurrent().getAttribute("date"),patientUid)==null) {
                            Log log = new Log(patientUid, (LocalDate) VaadinSession.getCurrent().getAttribute("date"), 2);
                            logService.getRepository().save(log);
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
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,formLayout,submitButton);
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout);
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
                UUID patientUID = userService.getRepository().findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUid(); // Current patient UID
                Integer state = 0;
                if (this.simpleLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUID,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                    state = 1;
                }
                if(comprehensiveLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUID,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                    state = 2;
                }
                if(state == 1){
                    Notification notification = Notification.show("You already entered value for this entry. You will override past data");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                if(state == 2){
                    Notification notification = Notification.show("You already entered value for this entry in comprehensive log book.",5000, Notification.Position.MIDDLE);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
        meal.addValueChangeListener(e->{
            if(!prepost.isEmpty()&& !meal.isEmpty()){
                Integer time = 0;
                String TimeString = prepost.getValue()+meal.getValue();
                if (TimeString.equals("PreBreakfast")) time =1;
                else if (TimeString.equals("PostBreakfast")) time =2;
                else if (TimeString.equals("PreLunch")) time =3;
                else if (TimeString.equals("PostLunch")) time =4;
                else if (TimeString.equals("PreDinner")) time =5;
                else if (TimeString.equals("PostDinner")) time =6;
                UUID patientUID = userService.getRepository().findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUid(); // Current patient UID
                Integer state = 0;
                if (this.simpleLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUID,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                    state = 1;
                }
                if(comprehensiveLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUID,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                    state = 2;
                }
                if(state == 2){
                    Notification notification = Notification.show("You already entered value for this entry. You will override past data");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                if(state == 1){
                    Notification notification = Notification.show("You already entered value for this entry in Simple log book.",5000, Notification.Position.MIDDLE);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    submitButton.setEnabled(false);
                }
            }
        });
    }


    private void init() {
        this.prepost = new ComboBox<>("Pre/Post");
        this.meal = new ComboBox<>("Meal");
        this.bloodGlucose = new NumberField("Blood Glucose");
        this.carbohydrate = new NumberField("Carbohydrate");
        this.insulinDose = new NumberField("Insulin Dose");

        Div bloodGlucoseUnit = new Div();
        bloodGlucoseUnit.setText("mmol/L");
        bloodGlucose.setSuffixComponent(bloodGlucoseUnit);

        Div carbsUnit = new Div();
        carbsUnit.setText("g");
        carbohydrate.setSuffixComponent(carbsUnit);

        Div insulinDoseUnit = new Div();
        insulinDoseUnit.setText("unit(s)");
        insulinDose.setSuffixComponent(insulinDoseUnit);

        prepost.setItems("Pre","Post");
        meal.setItems("Breakfast","Lunch","Dinner");
    }

}
