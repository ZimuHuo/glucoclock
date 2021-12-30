package com.glucoclock.database.comprehensiveLogBook_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "Comprehensivelogbook_db")
public class ComprehensiveLogBook implements Serializable, Comparable<ComprehensiveLogBook>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PatientId")
    private Long patientid;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name="Time")
    private int time;

    @Column(name = "BloodGlucose")
    private String bloodglucose;

    @Column(name = "CarbIntake")
    private String carbintake;

    @Column(name="InsulinDose")
    private String insulindose;



    public ComprehensiveLogBook(Long PatientId, LocalDate Date, int Time, String BloodGlucose, String CarbIntake, String InsulinDose) {

        patientid = PatientId;
        date = Date;
        bloodglucose = BloodGlucose;
        carbintake = CarbIntake;
        time=Time;
        insulindose=InsulinDose;
    }

    public ComprehensiveLogBook() {

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

    public String getTimeString(){
        String TimeString= new String();
        if(time==1) TimeString="Pre Breakfast";
        if(time==2) TimeString="Post Breakfast";
        if(time==3) TimeString="Pre Lunch";
        if(time==4) TimeString="Post Lunch";
        if(time==5) TimeString="Post Dinner";
        if(time==6) TimeString="Post Dinner";
        return TimeString;
    }

    @Override
    public int compareTo(ComprehensiveLogBook that){
        int returnint=2;
        int thisindex=this.getTime();
        int thatindex=that.getTime();
        if(thisindex<thatindex) returnint=-1;
        if(thisindex==thatindex) returnint=0;
        if(thisindex>thatindex) returnint=1;
        return returnint;
    }
    @Override
    public String toString() {
        return  "Comprehensive" +
                "," + date +
                "," + this.getTimeString() +
                "," + bloodglucose +
                "," + carbintake +
                "," + insulindose+
                "," + "-" +
                "," + "-" +
                "," + "-" +
                "," + "-";
    }
}