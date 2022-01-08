package com.glucoclock.database;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public abstract class Logbook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "PatientUid")
    protected UUID patientuid;

    @Column(name = "Date")
    protected LocalDate date;

    @Column(name="Time")
    protected int time;

    @Column(name = "BloodGlucose")
    protected String bloodglucose;

    @Column(name = "CarbIntake")
    protected String carbintake;


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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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
