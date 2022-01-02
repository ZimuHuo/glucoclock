package com.glucoclock.database.notifications_db;

import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.doctor.ViewPatientsData;
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

    @Column(name = "UID")
    private UUID uid;

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


//    Message


//    Constructor
    public Notification(
            PatientService patientService,
            UUID uid,
            LocalDate date,
            String requestType,
            String status){
        this.uid = uid;
        this.patientfirstname = patientService.getRepository().getPatientByUid(uid).getFirstName();
        this.patientlastname = patientService.getRepository().getPatientByUid(uid).getLastName();
        this.date = date;
        this.requesttype = requestType;
        this.status = status;
    }

    public Notification(){    }



//    Getters and setters
    public UUID getUid() {return uid;}
    public void setUid(UUID uid) {this.uid = uid;}

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
