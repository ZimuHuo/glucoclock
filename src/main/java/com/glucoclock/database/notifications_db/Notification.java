package com.glucoclock.database.notifications_db;

import com.glucoclock.database.patients_db.service.PatientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Notification_db")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PatientUID")
    private UUID patientuid;

    @Column(name = "DoctorUID")
    private UUID doctoruid;

    @Column(name = "PatientFirstName")
    private String patientfirstname;

    @Column(name = "PatientLastName")
    private String patientlastname;

//    Date of request
    @Column(name = "Date")
    private LocalDate date;

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
    public Notification(
            PatientService patientService,
            UUID patientuid,
            UUID doctoruid,
            LocalDate date,
            String requestType){
        this.patientuid = patientuid;
        this.doctoruid = doctoruid;
        this.patientfirstname = patientService.getRepository().getPatientByUid(patientuid).getFirstName();
        this.patientlastname = patientService.getRepository().getPatientByUid(patientuid).getLastName();
        this.date = date;
        this.requesttype = requestType;
        this.status = "Unresolved";

//        Set the message
        if (requesttype.equals("alarm") ) {
            shortmessage = "Blood glucose level " + /*Get value here*/  " units";
            completemessage = "something";
        }
        else if (requesttype.equals("questionnaire")) {
            shortmessage = "something";
            completemessage = "something";

//            set a max length of short message
            if (shortmessage.length() >= 10 /*exact number need to be determined*/) {
                shortmessage = shortmessage.substring(0, 9) + "...";
            }
        }
        else if (requesttype.equals("addPatient")) {
            shortmessage = "something";
            completemessage = "something";
        }


    }

    public Notification(){

    }



//    Getters and setters
    public long getNotificationId() {
        return id;
    }

    public UUID getPatientUid() {
        return patientuid;
    }
    public void setPatientUid(UUID patientuid) {
        this.patientuid = patientuid;
    }

    public UUID getDoctorUid() {
        return doctoruid;
    }
    public void setDoctorUid(UUID doctoruid) {
        this.doctoruid = doctoruid;
    }

    public String getPatientFirstName() {
        return patientfirstname;
    }
    public void setPatientFirstName(String patientFirstName) {
        this.patientfirstname = patientFirstName;
    }

    public String getPatientLastName() {
        return patientlastname;
    }
    public void setPatientLastName(String patientLastName) {
        this.patientlastname = patientLastName;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

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
