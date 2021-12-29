package com.glucoclock.database.intensiveLogBook_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Intensivelogbook_db")
public class IntensiveLogBook implements Serializable, Comparable<IntensiveLogBook> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PatientId")
    private Long patientid;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name="Time")
    private LocalTime time;

    @Column(name = "BloodGlucose")
    private String bloodglucose;

    @Column(name = "CarbIntake")
    private String carbintake;

    @Column(name="InsulinDose")
    private String insulindose;

    @Column (name="CarbBolus")
    private String carbbolus;

    @Column (name="HighBSBolus")
    private String highbsbolus;

    @Column (name="BasalRate")
    private String basalrate;

    @Column (name="Ketons")
    private String ketons;



    public IntensiveLogBook(Long PatientId, LocalDate Date, LocalTime Time, String BloodGlucose, String CarbIntake, String InsulinDose, String CarbBolus, String HighBSBolus, String BasalRate, String Ketons) {

        patientid = PatientId;
        date = Date;
        bloodglucose = BloodGlucose;
        carbintake = CarbIntake;
        time=Time;
        insulindose=InsulinDose;
        carbbolus=CarbBolus;
        highbsbolus=HighBSBolus;
        basalrate=BasalRate;
        ketons=Ketons;
    }

    public IntensiveLogBook() {

    }

    public String getCarbbolus() {
        return carbbolus;
    }

    public void setCarbbolus(String carbbolus) {
        this.carbbolus = carbbolus;
    }

    public String getHighbsbolus() {
        return highbsbolus;
    }

    public void setHighbsbolus(String highbsbolus) {
        this.highbsbolus = highbsbolus;
    }

    public String getBasalrate() {
        return basalrate;
    }

    public void setBasalrate(String basalrate) {
        this.basalrate = basalrate;
    }

    public String getKetons() {
        return ketons;
    }

    public void setKetons(String ketons) {
        this.ketons = ketons;
    }

    public String getInsulindose() {
        return insulindose;
    }

    public void setInsulindose(String insulindose) {
        this.insulindose = insulindose;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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

    @Override
    public int compareTo(IntensiveLogBook that){
        return this.getTime().compareTo(that.getTime());
    }

    @Override
    public String toString() {
        return ","+"Intensive" +
                "," + date +
                "," + time.toString() +
                "," + bloodglucose +
                "," + carbintake +
                "," + insulindose +
                "," + carbbolus +
                "," + highbsbolus +
                "," + basalrate +
                "," + ketons;
    }
}