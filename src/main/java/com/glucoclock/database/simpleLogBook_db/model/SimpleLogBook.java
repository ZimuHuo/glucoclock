package com.glucoclock.database.simpleLogBook_db.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Simplelogbook_db")
public class SimpleLogBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PatientId")
    private Long patientid;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name="Time")
    private String time;

    @Column(name = "BloodGlucose")
    private String bloodglucose;

    @Column(name = "CarbIntake")
    private String carbintake;



    public SimpleLogBook(Long PatientId, LocalDate Date, String Time, String BloodGlucose, String CarbIntake) {

        patientid = PatientId;
        date = Date;
        bloodglucose = BloodGlucose;
        carbintake = CarbIntake;
        time=Time;
    }

    public SimpleLogBook() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBloodglucose() {
        return bloodglucose;
    }

    public void setBloodglucose(String bloodglucose) {
        this.bloodglucose = bloodglucose;
    }

    public String getCarbintake() {
        return carbintake;
    }

    public void setCarbintake(String carbintake) {
        this.carbintake = carbintake;
    }
}

