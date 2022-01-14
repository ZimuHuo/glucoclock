package com.glucoclock.database.comprehensiveLogBook_db.model;

import com.glucoclock.database.Logbook;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "Comprehensivelogbook_db")
public class ComprehensiveLogBook extends Logbook implements Comparable<ComprehensiveLogBook>{

    //Columns of the database and variables
    //Patient uid

    //Record Date
    @Column(name = "Date")
    private LocalDate date;

    //Record Time   (Pre/Post- Breakfast/Lunch/Dinner)
    @Column(name="Time")
    private int time;

    //Record Blood glucose
    @Column(name = "BloodGlucose")
    private String bloodglucose;

    //Record Carb intake
    @Column(name = "CarbIntake")
    private String carbintake;

    //Record insulindose
    @Column(name="InsulinDose")
    private String insulindose;


    //  Constructor
    public ComprehensiveLogBook(UUID PatientUid, LocalDate Date, String TimeString, String BloodGlucose, String CarbIntake, String InsulinDose) {
        patientuid = PatientUid;
        date = Date;
        bloodglucose = BloodGlucose;
        carbintake = CarbIntake;
        insulindose=InsulinDose;
        if (TimeString.equals("PreBreakfast")) time =1;
        else if (TimeString.equals("PostBreakfast")) time =2;
        else if (TimeString.equals("PreLunch")) time =3;
        else if (TimeString.equals("PostLunch")) time =4;
        else if (TimeString.equals("PreDinner")) time =5;
        else if (TimeString.equals("PostDinner")) time =6;
    }

    public ComprehensiveLogBook() {

    }

    //  Getter and Setter
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String getBloodglucose() {
        return bloodglucose;
    }

    @Override
    public void setBloodglucose(String bloodglucose) {
        this.bloodglucose = bloodglucose;
    }

    @Override
    public String getCarbintake() {
        return carbintake;
    }

    @Override
    public void setCarbintake(String carbintake) {
        this.carbintake = carbintake;
    }

    public String getInsulindose() {
        return insulindose;
    }

    public void setInsulindose(String insulindose) {
        this.insulindose = insulindose;
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
    //Compare record time of logs
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