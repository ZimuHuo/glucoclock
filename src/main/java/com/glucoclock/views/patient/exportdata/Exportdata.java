package com.glucoclock.views.patient.exportdata;

public class Exportdata {
    private Long patientid;
    private String date;
    private String time;
    private String bloodglucose;
    private String carbintake;
    private String insulindose;
    private String carbbolus;
    private String highbsbolus;
    private String basalrate;
    private String ketons;

    public Exportdata() {
        this.patientid = 0l;
        this.date = "-";
        this.time = "-";
        this.bloodglucose = "-";
        this.carbintake = "-";
        this.insulindose = "-";
        this.carbbolus = "-";
        this.highbsbolus = "-";
        this.basalrate = "-";
        this.ketons = "-";
    }

    @Override
    public String toString() {
        return "Exportdata{" +
                "patientid=" + patientid +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", bloodglucose='" + bloodglucose + '\'' +
                ", carbintake='" + carbintake + '\'' +
                ", insulindose='" + insulindose + '\'' +
                ", carbbolus='" + carbbolus + '\'' +
                ", highbsbolus='" + highbsbolus + '\'' +
                ", basalrate='" + basalrate + '\'' +
                ", ketons='" + ketons + '\'' +
                '}';
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getInsulindose() {
        return insulindose;
    }

    public void setInsulindose(String insulindose) {
        this.insulindose = insulindose;
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
}
