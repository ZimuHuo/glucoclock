package com.glucoclock.views.patient;

import java.time.LocalDate;

public class SimpleLogExample {
    private LocalDate Date;
    private String Time;
    private Integer BloodGlucose;
    private Integer CarbonIntake;
    public SimpleLogExample(LocalDate Date,String Time, Integer BloodGlucose,Integer CarbonIntake){
        this.Date=Date;
        this.Time=Time;
        this.BloodGlucose=BloodGlucose;
        this.CarbonIntake=CarbonIntake;
    }

    public Integer getBloodGlucose() {
        return BloodGlucose;
    }
    public Integer getCarbonIntake() {
        return CarbonIntake;
    }
    public String getTime() {
        return Time;
    }
    public LocalDate getDate() {
        return Date;
    }
}
