package com.glucoclock.database.intensiveLogBook_db.model;

import com.glucoclock.database.Logbook;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Intensivelogbook_db")
public class IntensiveLogBook extends Logbook implements Comparable<IntensiveLogBook> {


    @Column(name="Time")
    private LocalTime time;

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



    public IntensiveLogBook(UUID PatientUid, LocalDate Date, LocalTime Time, String BloodGlucose, String CarbIntake, String InsulinDose, String CarbBolus, String HighBSBolus, String BasalRate, String Ketons) {

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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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


    @Override
    public int compareTo(IntensiveLogBook that){
        return this.getTime().compareTo(that.getTime());
    }

    @Override
    public String toString() {
        return "Intensive" +
                "," + date +
                "," + time +
                "," + bloodglucose +
                "," + carbintake +
                "," + insulindose +
                "," + carbbolus +
                "," + highbsbolus +
                "," + basalrate +
                "," + ketons;
    }
}