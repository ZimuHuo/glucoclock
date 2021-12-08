package com.glucoclock.database.comprehensiveLogBook_db.model;

import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBookObject;

import java.time.LocalDate;


public class ComprehensiveLogBookObject extends SimpleLogBookObject {

    private String InsulinDose;

    public ComprehensiveLogBookObject(Long patient_id, LocalDate Date, String Time, String BloodGlucose, String CarbIntake, String InsulinDose) {
        super(patient_id, Date, Time, BloodGlucose, CarbIntake);
        this.InsulinDose=InsulinDose;
    }


    public String getInsulinDose() {
        return InsulinDose;
    }

    public void setInsulinDose(String insulinDose) {
        InsulinDose = insulinDose;
    }
}