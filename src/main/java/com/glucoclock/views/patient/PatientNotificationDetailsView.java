package com.glucoclock.views.patient;

import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.notifications_db.model.Notification;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Notification Details")
@Route(value = "patient/notification-details")
public class PatientNotificationDetailsView extends Div {
    private H3 space = new H3(" ");
    private TextArea msg = new TextArea("Message:");
    private TextArea replyMsg = new TextArea();
    private Button agreeBut = new Button("Accept");
    private Button backBut = new Button("Back");
    private MenuBar menu = new MenuBar("PNS");


    private final NotificationService notificationService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DoctorPatientService doctorPatientService;

    public PatientNotificationDetailsView(DoctorPatientService doctorPatientService, NotificationService notificationService, PatientService patientService, DoctorService doctorService){
        this.notificationService = notificationService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.doctorPatientService = doctorPatientService;
        Notification thisNotification = notificationService.getRepository().getNotificationById((long) VaadinSession.getCurrent().getAttribute("NotificationID")); // current notification

        setStyles(thisNotification);
        setNavigation(doctorPatientService, notificationService, thisNotification);

        HorizontalLayout buttons = new HorizontalLayout(agreeBut,backBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(menu,space,vl);
    }

    private void setStyles(Notification thisNotification){
        agreeBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agreeBut.setVisible(false);
        backBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        msg.setValue(thisNotification.getCompleteMessage());
        msg.setReadOnly(true);
        msg.setWidth("50%");
        msg.setMinHeight("80%");
        msg.setMaxHeight("300px");
        replyMsg.setWidth("50%");
        replyMsg.setMinHeight("80%");
        replyMsg.setMaxHeight("300px");

//        Patient can only reply to add patient request
        if (thisNotification.getRequestType().equals("Add Patient Request")) {

//            Allow the patient to reply to the request
            if (thisNotification.getStatus().equals("Unresolved")) {
                replyMsg.setVisible(false);
                agreeBut.setVisible(true);

//            Show the reply
            } else {
                replyMsg.setLabel("Your reply:");
                replyMsg.setValue(thisNotification.getReplymessage());
                replyMsg.setReadOnly(true);
            }

        } else {

            if (thisNotification.getStatus().equals("Unresolved")){
                replyMsg.setVisible(false);

//                Let the patient view the reply if resolved
            } else {
                replyMsg.setLabel("Reply from doctor:");
                replyMsg.setValue(thisNotification.getReplymessage());
                replyMsg.setReadOnly(true);
            }
        }


    }

    private void setNavigation(DoctorPatientService doctorPatientService, NotificationService notificationService, Notification thisNotification){
        agreeBut.addClickListener(e->{
            com.vaadin.flow.component.notification.Notification.show("Reply sent").addThemeVariants(NotificationVariant.LUMO_SUCCESS);

            //add to doctor patient database
            doctorPatientService.create(thisNotification.getPatientUid(),thisNotification.getDoctorUid());

//            Resolve the request
            notificationService.resolveRequest((long)VaadinSession.getCurrent().getAttribute("NotificationID"));

//            Set the reply message
            notificationService.reply((long)VaadinSession.getCurrent().getAttribute("NotificationID"), thisNotification.getPatientFirstName() + " " + thisNotification.getPatientLastName() + " has accepted the add patient request.");


            agreeBut.getUI().ifPresent(ui ->
                    ui.navigate(PatientNotificationView.class)
            );
        });
        backBut.addClickListener(e->
                backBut.getUI().ifPresent(ui ->
                        ui.navigate(PatientNotificationView.class)
                ));
    }
}