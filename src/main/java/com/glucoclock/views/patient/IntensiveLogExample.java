package com.glucoclock.views.patient;

import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.time.LocalDate;

public class IntensiveLogExample extends SimpleLogExample{
    Integer CarbBolus;
    Integer HighBSBolus;
    Integer BasalRage;
    Integer Ketones;

    public IntensiveLogExample(LocalDate Date, String Time, Integer BloodGlucose, Integer CarbonIntake,Integer CarBolus,Integer HighBSBolus,Integer BasalRage,Integer Ketones) {
        super(Date, Time, BloodGlucose, CarbonIntake);
        this.CarbBolus=CarBolus;
        this.HighBSBolus=HighBSBolus;
        this.BasalRage= BasalRage;
        this.Ketones=Ketones;
    }

    public Integer getCarbBolus() {
        return CarbBolus;
    }

    public Integer getHighBSBolus() {
        return HighBSBolus;
    }

    public Integer getBasalRage() {
        return BasalRage;
    }

    public Integer getKetones() {
        return Ketones;
    }
}
