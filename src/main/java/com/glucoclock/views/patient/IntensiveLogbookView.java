package com.glucoclock.views.patient;

import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.notifications_db.NotificationService;
import com.glucoclock.database.notifications_db.Notifications;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
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
    private TextField bloodGlucose;
    private TextField carbohydrateIntake;
    private TextField insulinDose;
    private TextField carbBolus;
    private TextField highBsBolus;
    private TextField basalRate;
    private TextField ketones;
    private int hour;
    private H3 title = new H3("Add Intensive Logbook Entry");
    private MenuBar menu = new MenuBar("PNS");

    private final UserService userService;
    private final IntensiveLogBookService intensiveLogBookService;
    private final NotificationService notificationService;
    private final PatientService patientService;
    private final DoctorPatientService doctorPatientService;

    public IntensiveLogbookView(UserService userService, IntensiveLogBookService intensiveLogBookService, NotificationService notificationService, PatientService patientService, DoctorPatientService doctorPatientService) {
        this.userService = userService;
        this.intensiveLogBookService = intensiveLogBookService;
        this.notificationService = notificationService;
        this.patientService = patientService;
        this.doctorPatientService = doctorPatientService;
        init();
        add(menu);
        //add(menuBar());
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
        Button submitButton = new Button("Upload");
        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e ->{
            //check input validity
            try{
                float bg = Integer.parseInt(bloodGlucose.getValue());
                //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                if(bg>140){
//                    SendMail sendMail = new SendMail();
//                    sendMail.sendMail("Act now","Glucose is high","Zimuhuo@outlook.com");
                    Notification.show("Abnormal Blood Glucose Level").addThemeVariants(NotificationVariant.LUMO_ERROR);//change to save to notification db later
                }
                //save to database
                UUID uid = userService.getRepository().findAll().get(0).getUid();
                IntensiveLogBook intensiveLogBook = new IntensiveLogBook(
                        uid,
                        (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                        timePicker.getValue(),
                        bloodGlucose.getValue(),
                        carbohydrateIntake.getValue(),
                        insulinDose.getValue(),
                        carbBolus.getValue(),
                        highBsBolus.getValue(),
                        basalRate.getValue(),
                        ketones.getValue()

                );

                // Create and save a new notification
                UUID patientUID = userService.getRepository().findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUid(); // Current patient UID
                Notifications n = new Notifications(
                        patientService,
                        patientUID,
                        doctorPatientService.getRepository().getDoctorPatientByPatientuid(patientUID).getDoctoruid(), // Doctor uid
                        "Alarm"
                );
                n.setShortMessage("Blood glucose level " + bloodGlucose.getValue() + " units");
                n.setCompleteMessage(
                        n.getPatientFirstName() +" "+ n.getPatientLastName() +" is experiencing abnormal blood glucose levels.\n" +
                                "\n" +
                                "Date: " + n.getDate().toLocalDate() + "\n" +
                                "Time: " + n.getDate().toLocalTime() + "\n" +
                                "Blood glucose level: " + bloodGlucose.getValue() + "units."
                );
                notificationService.getRepository().save(n);


//UUID PatientUid, LocalDate Date, LocalTime Time, String BloodGlucose, String CarbIntake, String InsulinDose, String CarbBolus, String HighBSBolus, String BasalRate, String Ketons
                intensiveLogBookService.getRepository().save(intensiveLogBook);

                //Navigation
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(ConfirmationView.class)
                );
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
                Notification.show("Invalid input(s), please re-enter");
            }

        }

        );
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,submitButton);
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout);
    }

    private void init() {
        this.timePicker = new TimePicker("Time");
        setTimePicker();
        this.bloodGlucose = new TextField("Blood Glucose");
        this.carbohydrateIntake = new TextField("Carbohydrate Intake");
        this.insulinDose = new TextField("Insulin Dose");
        this.carbBolus = new TextField("Carb Bolus");
        this.highBsBolus = new TextField("High BS Bolus");
        this.basalRate = new TextField("Basal Rate");
        this.ketones = new TextField("Ketones");
        setClearButtonVisible();
        setHelperText();
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

    private void setHelperText() {
        bloodGlucose.setHelperText("unit");
        carbohydrateIntake.setHelperText("unit");
        insulinDose.setHelperText("unit");
        carbBolus.setHelperText("unit");
        highBsBolus.setHelperText("unit");
        basalRate.setHelperText("unit");
        ketones.setHelperText("unit");
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


}

