package com.glucoclock.database.notifications_db.model;

import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.doctor.DoctorNotificationDetailsView;
import com.glucoclock.views.patient.PatientNotificationDetailsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "Notification_db")
public class Notifications implements Serializable, Comparable<Notifications>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PatientUID")
    private UUID patientuid;

    @Column(name = "DoctorUID")
    private UUID doctoruid;

    @Column(name = "PatientFirstName")
    private String patientFirstName;

    @Column(name = "PatientLastName")
    private String patientLastName;

//    Date and time of request
    @Column(name = "Date")
    private LocalDateTime date;

    @Column(name = "RequestType")
    private String requesttype;

//    Status of request (resolved/unresolved)
    @Column(name = "Status")
    private String status;

    @Column(name = "ShortMessage")
    private String shortmessage;

    @Column(name = "CompleteMessage")
    private String completemessage;

    @Column(name = "ReplyMessage")
    private String replymessage;


//    Constructor of a new notification
    public Notifications(
            PatientService patientService,
            UUID patientuid,
            UUID doctoruid,
            String requestType){
        this.patientuid = patientuid;
        this.patientFirstName = patientService.getRepository().getPatientByUid(patientuid).getFirstName();
        this.patientLastName = patientService.getRepository().getPatientByUid(patientuid).getLastName();
        this.doctoruid = doctoruid;
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.requesttype = requestType;
        this.status = "Unresolved";

//        Message is set in frontend classes
    }

    public Notifications(){}



//    Getters and setters
    public long getNotificationId() {
        return id;
    }

    public UUID getPatientUid() {
        return patientuid;
    }

    public UUID getDoctorUid() {
        return doctoruid;
    }

    public String getPatientFirstName() {return patientFirstName;}
    public void setPatientFirstName(String patientFirstName) {this.patientFirstName = patientFirstName;}

    public String getPatientLastName() {return patientLastName;}
    public void setPatientLastName(String patientLastName) {this.patientLastName = patientLastName;}

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {this.date = date;}

    public LocalDate getDateNoTime() {return date.toLocalDate();}

    public String getRequestType() {
        return requesttype;
    }
    public void setRequestType(String requestType) {
        this.requesttype = requestType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getShortMessage() {
        return shortmessage;
    }
    public void setShortMessage(String shortmessage) {
        this.shortmessage = shortmessage;
    }

    public String getCompleteMessage() {
        return completemessage;
    }
    public void setCompleteMessage(String completemessage) {
        this.completemessage = completemessage;
    }

    public String getReplymessage() {return replymessage;}
    public void setReplymessage(String replymessage) {this.replymessage = replymessage;}

    public Span buildStatusBadge(){
        Span statusBadge = new Span(status);
        if (status.equals("Unresolved")){
            statusBadge.getElement().getThemeList().add("badge error");
        }
        else{
            statusBadge.getElement().getThemeList().add("badge success");
        }
        return statusBadge;
    }

//   generate the View Details button on doctor page
    public Button buildDoctorViewButton() {
        Button button = new Button("View Details");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click->{
            VaadinSession.getCurrent().setAttribute("NotificationID", id);
            button.getUI().ifPresent(ui->ui.navigate(DoctorNotificationDetailsView.class)); //change navigation class
        });
        return button;
    }

    //   generate the View Details button on patient page
    public Button buildPatientViewButton() {
        Button button = new Button("View Details");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click->{
            VaadinSession.getCurrent().setAttribute("NotificationID", id);
            button.getUI().ifPresent(ui->ui.navigate(PatientNotificationDetailsView.class)); //change navigation class
        });
        return button;
    }

    @Override
    public int compareTo(Notifications that) {
        //show the nearest notification at front
        return -1*this.date.compareTo(that.date);
    }
}
