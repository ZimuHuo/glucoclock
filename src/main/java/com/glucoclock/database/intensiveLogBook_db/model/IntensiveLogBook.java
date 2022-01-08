package com.glucoclock.database.intensiveLogBook_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Intensivelogbook_db")
public class IntensiveLogBook implements Serializable, Comparable<IntensiveLogBook> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Columns of the database and variables
    //Patient uid
    @Column(name = "PatientUid")
    private UUID patientuid;

    //Record date
    @Column(name = "Date")
    private LocalDate date;

    //Record time (1:00,2:00...)
    @Column(name="Time")
    private LocalTime time;

    //Recorded blood glucose
    @Column(name = "BloodGlucose")
    private String bloodglucose;

    //Record carb intake
    @Column(name = "CarbIntake")
    private String carbintake;

    //Record insulin dose
    @Column(name="InsulinDose")
    private String insulindose;

    //Record carb bolus
    @Column (name="CarbBolus")
    private String carbbolus;

    //Record high bs bolus
    @Column (name="HighBSBolus")
    private String highbsbolus;

    //Record basal rate
    @Column (name="BasalRate")
    private String basalrate;

    //Record ketones
    @Column (name="Ketons")
    private String ketons;


    //  Constructor
    public IntensiveLogBook(UUID PatientUid, LocalDate Date,
                            LocalTime Time, String BloodGlucose,
                            String CarbIntake, String InsulinDose,
                            String CarbBolus, String HighBSBolus,
                            String BasalRate, String Ketons) {

        patientuid = PatientUid;
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

    //   getter and setter
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

    public UUID getPatientuid() {
        return patientuid;
    }

    public void setPatientuid(UUID patientuid) {
        this.patientuid = patientuid;
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
        return "Intensive" +
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