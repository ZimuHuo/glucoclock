package com.glucoclock.database.simpleLogBook_db.model;

import java.time.LocalDate;

public class SimpleLogBookObject {

    private Long PatientId;
    private LocalDate Date;
    private String BloodGlucose;
    private String CarbIntake;
    private String Time;



    public SimpleLogBookObject(Long patient_id,LocalDate Date, String Time, String BloodGlucose, String CarbIntake) {
        this.PatientId=patient_id;
        this.Date=Date;
        this.BloodGlucose=BloodGlucose;
        this.CarbIntake=CarbIntake;
        this.Time=Time;
    }

    public Long getPatientId() {
        return PatientId;
    }

    public void setPatientId(Long patientId) {
        PatientId = patientId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getBloodGlucose() {
        return BloodGlucose;
    }

    public void setBloodGlucose(String bloodGlucose) {
        BloodGlucose = bloodGlucose;
    }

    public String getCarbIntake() {
        return CarbIntake;
    }

    public void setCarbIntake(String carbIntake) {
        CarbIntake = carbIntake;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
