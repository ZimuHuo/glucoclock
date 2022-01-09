package com.glucoclock.views.patient;

import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.util.SendMail;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;


@PageTitle("Add Intensive Logbook Entry")
@Route(value = "patient/add-intensive-logbook-entry")
public class IntensiveLogbookView extends Div {
    private TimePicker timePicker;
    private NumberField bloodGlucose;
    private NumberField carbohydrateIntake;
    private NumberField insulinDose;
    private NumberField carbBolus;
    private NumberField highBsBolus;
    private NumberField basalRate;
    private NumberField ketones;
    private int hour;
    private H3 title = new H3("Add Intensive Logbook Entry");
    private MenuBar menu = new MenuBar("PNS");
    private Button submitButton = new Button("Upload");

    private LocalTime time;

    private UUID patientUid;

    private final UserService userService;
    private final IntensiveLogBookService intensiveLogBookService;
    private final NotificationService notificationService;
    private final PatientService patientService;
    private final DoctorPatientService doctorPatientService;
    private final LogService logService;
    private final DoctorService doctorService;

    public IntensiveLogbookView(UserService userService, IntensiveLogBookService intensiveLogBookService, NotificationService notificationService, PatientService patientService, DoctorPatientService doctorPatientService, LogService logService, DoctorService doctorService) {
        this.userService = userService;
        this.intensiveLogBookService = intensiveLogBookService;
        this.notificationService = notificationService;
        this.patientService = patientService;
        this.doctorPatientService = doctorPatientService;
        this.logService = logService;
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
        this.timePicker = new TimePicker("Time");
        setTimePicker();
        this.bloodGlucose = new NumberField("Blood Glucose");
        this.carbohydrateIntake = new NumberField("Carbohydrate Intake");
        this.insulinDose = new NumberField("Insulin Dose");
        this.carbBolus = new NumberField("Carb Bolus");
        this.highBsBolus = new NumberField("High BS Bolus");
        this.basalRate = new NumberField("Basal Rate");
        this.ketones = new NumberField("Ketones");
        setClearButtonVisible();
        setUnits();

        LocalTime localTime = timePicker.getValue();
        time = LocalTime.of(localTime.getHour(), 00, 00);

        timePicker.addValueChangeListener(e->{
            LocalTime nowTime = timePicker.getValue();
            time = LocalTime.of(nowTime.getHour(), 00, 00);

            if(intensiveLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"))!=null){
                com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("You already entered value for this entry. You will override past data");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

        });

    }

    private void setTimePicker() {
        timePicker.setStep(Duration.ofHours(1));
        ZonedDateTime now = ZonedDateTime.now();
        if(now.getMinute()<=30){
            hour = now.getHour();
        }else{
            if(now.getHour()<23){
                hour = now.getHour()+1;
            }
        }
        timePicker.setValue(LocalTime.of(hour, 0));
    }

    private void setUnits() {
        Div bloodGlucoseUnit = new Div();
        bloodGlucoseUnit.setText("mmol/L");
        bloodGlucose.setSuffixComponent(bloodGlucoseUnit);

        Div carbsUnit = new Div();
        carbsUnit.setText("g");
        carbohydrateIntake.setSuffixComponent(carbsUnit);

        Div insulinDoseUnit = new Div();
        insulinDoseUnit.setText("unit(s)");
        insulinDose.setSuffixComponent(insulinDoseUnit);

        Div carbBolusUnit = new Div();
        carbBolusUnit.setText("unit(s)");
        carbBolus.setSuffixComponent(carbBolusUnit);

        Div highBSBolusUnit = new Div();
        highBSBolusUnit.setText("unit(s)");
        highBsBolus.setSuffixComponent(highBSBolusUnit);

        Div basalRateUnit = new Div();
        basalRateUnit.setText("u/h");
        basalRate.setSuffixComponent(basalRateUnit);

        Div ketonesUnit = new Div();
        ketonesUnit.setText("mmol/L");
        ketones.setSuffixComponent(ketonesUnit);
    }

    private void setClearButtonVisible() {
        bloodGlucose.setClearButtonVisible(true);
        carbohydrateIntake.setClearButtonVisible(true);
        insulinDose.setClearButtonVisible(true);
        carbBolus.setClearButtonVisible(true);
        highBsBolus.setClearButtonVisible(true);
        basalRate.setClearButtonVisible(true);
        ketones.setClearButtonVisible(true);
    }

    private Component createFields(){
        var formLayout = new FormLayout();
        formLayout.add(
                timePicker,
                bloodGlucose,
                carbohydrateIntake,
                insulinDose,
                carbBolus,
                highBsBolus,
                basalRate,
                ketones
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 1)
        );

        formLayout.setColspan(timePicker, 1);
        formLayout.setColspan(bloodGlucose,1 );
        formLayout.setColspan(carbohydrateIntake,1 );
        formLayout.setColspan(insulinDose,1 );
        formLayout.setColspan(carbBolus,1 );
        formLayout.setColspan(highBsBolus,1 );
        formLayout.setColspan(basalRate,1 );
        formLayout.setColspan(ketones,1);

        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //Submit button
        submitButton.addClickListener(e -> {
            //check is there a data at same date and time
            IntensiveLogBook intensive = intensiveLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,time,(LocalDate) VaadinSession.getCurrent().getAttribute("date"));
                    if (bloodGlucose.isEmpty()) {
                        com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("Your glucose level is empty");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (timePicker.isEmpty()) {
                        com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("Please select your time correctly");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (carbohydrateIntake.isEmpty() || insulinDose.isEmpty() || carbohydrateIntake.isEmpty() || carbBolus.isEmpty() || highBsBolus.isEmpty() || basalRate.isEmpty() || ketones.isEmpty()) {
                        com.vaadin.flow.component.notification.Notification notification = com.vaadin.flow.component.notification.Notification.show("Please check your entries");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else {
                        //if there is no data
                        //Notification
                        //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                        Double bg = bloodGlucose.getValue();
                        //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                        if (bg > 140) {
                            com.vaadin.flow.component.notification.Notification.show("Abnormal Blood Glucose Level").addThemeVariants(NotificationVariant.LUMO_ERROR);//change to save to notification db later

                            //if patient do not have a doctor don't send email
                            if(doctorPatientService.checkPatient(patientUid)) {
                                Notifications n = new Notifications(
                                        patientService,
                                        patientUid,
                                        doctorPatientService.getRepository().getDoctorPatientByPatientuid(patientUid).getDoctoruid(), // Doctor uid
                                        "Blood Glucose Alarm"
                                );
                                String doctorEmail = doctorService.getRepository().getDoctorByUid(doctorPatientService.getRepository().getDoctorPatientByPatientuid(patientUid).getDoctoruid()).getEmail();

                                SendMail sendMail = new SendMail();
                                sendMail.sendMail("Act now",n.getPatientFirstName() + " " + n.getPatientLastName() + " is experiencing abnormal blood glucose levels.\n" +
                                        "\n" +
                                        "Date: " + n.getDate().toLocalDate() + "\n" +
                                        "Time: " + n.getDate().toLocalTime() + "\n" +
                                        "Blood glucose level: " + bloodGlucose.getValue() + " mmol/L.",doctorEmail);


                                // Create and save a new notification
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

                        if(intensive == null) {
                            //no data entered before
                            //Create a new simple logbook
                            IntensiveLogBook intensiveLogBook = new IntensiveLogBook(
                                    patientUid,
                                    (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                                    time,
                                    bloodGlucose.getValue().toString(),
                                    carbohydrateIntake.getValue().toString(),
                                    insulinDose.getValue().toString(),
                                    carbBolus.getValue().toString(),
                                    highBsBolus.getValue().toString(),
                                    basalRate.getValue().toString(),
                                    ketones.getValue().toString()

                            );
                            //add intensive log to database
                            intensiveLogBookService.getRepository().save(intensiveLogBook);

                            //check if this is the first log of the day, add data to logdb
                            if (logService.findLogBookbyDate((LocalDate) VaadinSession.getCurrent().getAttribute("date"), patientUid) == null) {
                                Log log = new Log(patientUid, (LocalDate) VaadinSession.getCurrent().getAttribute("date"), 3);
                                logService.getRepository().save(log);
                            }
                        }
                        else{
                            //already have data, need to replace
                            intensiveLogBookService.updateBloodGlucose(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,bloodGlucose.getValue().toString());
                            intensiveLogBookService.updateCarbIntake(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,carbohydrateIntake.getValue().toString());
                            intensiveLogBookService.updateInsulinDose(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,insulinDose.getValue().toString());
                            intensiveLogBookService.updateCarbBolus(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,carbBolus.getValue().toString());
                            intensiveLogBookService.updateHighBSBolus(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,highBsBolus.getValue().toString());
                            intensiveLogBookService.updateBasalRate(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,basalRate.getValue().toString());
                            intensiveLogBookService.updateKetones(patientUid,(LocalDate) VaadinSession.getCurrent().getAttribute("date"),time,ketones.getValue().toString());
                        }

                        //Navigation
                        submitButton.getUI().ifPresent(ui ->
                                ui.navigate(ConfirmationView.class)
                        );
                    }
                }
        );
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, title, submitButton);
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return horizontalLayout;
    }


}

