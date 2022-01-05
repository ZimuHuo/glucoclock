package com.glucoclock.database.notifications_db;

import com.glucoclock.database.patients_db.service.PatientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "Notification_db")
public class Notifications implements Serializable {

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


//    Constructor of a new notification
    public Notifications(
            PatientService patientService,
            UUID patientuid,
            UUID doctoruid,
            String requestType){
        Notification.show("5",1000, Notification.Position.TOP_CENTER);
        this.patientuid = patientuid;
        Notification.show("6",1000, Notification.Position.TOP_CENTER);
        this.patientFirstName = patientService.getRepository().getPatientByUid(patientuid).getFirstName();
        Notification.show("7",1000, Notification.Position.TOP_CENTER);
        this.patientLastName = patientService.getRepository().getPatientByUid(patientuid).getLastName();
        Notification.show("8",1000, Notification.Position.TOP_CENTER);
        this.doctoruid = doctoruid;
        Notification.show("9",1000, Notification.Position.TOP_CENTER);
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Notification.show("10",1000, Notification.Position.TOP_CENTER);
        this.requesttype = requestType;
        Notification.show("11",1000, Notification.Position.TOP_CENTER);
        this.status = "Unresolved";
        Notification.show("12",1000, Notification.Position.TOP_CENTER);

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

    public Span buildStatusBadge(){
        Span statusBadge = new Span(status);
        if (status == "Unresolved"){
            statusBadge.getElement().getThemeList().add("badge error");
        }
        else{
            statusBadge.getElement().getThemeList().add("badge success");
        }
        return statusBadge;
    }

    public Button buildViewButton() {
        Button button = new Button("View Details");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        button.addClickListener(click->{
//            button.getUI().ifPresent(ui->ui.navigate(ViewPatientsData.class)); //change navigation class
//            com.vaadin.flow.component.notification.Notification.show(patientFirstName+patientLastName+date+requestType+status);
//        });
        return button;
    }
}
