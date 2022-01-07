package com.glucoclock.views.doctor;

import com.glucoclock.database.doctors_db.service.DoctorService;
import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Notification Details")
@Route(value = "doctor/notification-details")
public class DoctorNotificationDetailsView extends Div {
    private H3 title = new H3();
    private TextArea msg = new TextArea("Message:");
    private TextArea replyMsg = new TextArea();
    private Button sendBut = new Button("Send");
    private Button backBut = new Button("Back");
    private MenuBar menu = new MenuBar("DNS");


    private final NotificationService notificationService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public DoctorNotificationDetailsView(NotificationService notificationService, PatientService patientService, DoctorService doctorService){
        this.notificationService = notificationService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        Notifications thisNotification = notificationService.getRepository().getNotificationById((long)VaadinSession.getCurrent().getAttribute("NotificationID")); // current notification

        setStyles(thisNotification);
        setNavigation(notificationService, thisNotification);

        HorizontalLayout buttons = new HorizontalLayout(sendBut,backBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(menu,vl);
    }

    private void setStyles(Notifications thisNotification){
        sendBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sendBut.setVisible(false);
        backBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        msg.setValue(thisNotification.getCompleteMessage());
        msg.setReadOnly(true);
        msg.setWidth("50%");
        msg.setMinHeight("80%");
        msg.setMaxHeight("300px");
        replyMsg.setWidth("50%");
        replyMsg.setMinHeight("80%");
        replyMsg.setMaxHeight("300px");

//        Doctor cannot reply if it is an add patient request
        if (thisNotification.getRequestType().equals("Add Patient Request")) {

            if (thisNotification.getStatus().equals("Unresolved")) {
                replyMsg.setVisible(false);

            } else {
                replyMsg.setLabel("Reply from patient");
                replyMsg.setValue(thisNotification.getReplymessage());
                replyMsg.setReadOnly(true);
            }

//        Let the doctor reply or view his reply
        } else {

//        allow the user to reply if the request is unresolved
            if (thisNotification.getStatus().equals("Unresolved")){
                replyMsg.setLabel("Reply Here:");
                replyMsg.setClearButtonVisible(true);
                sendBut.setVisible(true);

//            Show the replied message if the request is resolved
            } else {
                replyMsg.setLabel("Your reply");
                replyMsg.setValue(thisNotification.getReplymessage());
                replyMsg.setReadOnly(true);
            }
        }


    }

    private void setNavigation(NotificationService notificationService, Notifications thisNotification){
        sendBut.addClickListener(e->{
            Notification.show("Reply sent").addThemeVariants(NotificationVariant.LUMO_SUCCESS);

//            Set the reply message
            notificationService.reply((long)VaadinSession.getCurrent().getAttribute("NotificationID"),replyMsg.getValue());

//            Resolve the request
            notificationService.resolveRequest((long)VaadinSession.getCurrent().getAttribute("NotificationID"));




                    sendBut.getUI().ifPresent(ui ->
                            ui.navigate(DoctorNotificationView.class)
                    );
        });
        backBut.addClickListener(e->
                backBut.getUI().ifPresent(ui ->
                        ui.navigate(DoctorNotificationView.class)
                ));
    }
}
