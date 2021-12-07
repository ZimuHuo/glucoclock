package com.glucoclock.views.patient;

import java.time.LocalDate;

public class CompLogExample extends SimpleLogExample{
    private Integer InsulinDose;

    public CompLogExample(LocalDate Date, String Time, Integer BloodGlucose, Integer CarbonIntake,Integer InsulinDose) {
        super(Date, Time, BloodGlucose, CarbonIntake);
        this.InsulinDose=InsulinDose;
    }
    public Integer getInsulinDose() {
        return InsulinDose;
    }
}
