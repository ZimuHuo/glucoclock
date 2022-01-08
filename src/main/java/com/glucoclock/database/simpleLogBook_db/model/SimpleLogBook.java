package com.glucoclock.database.simpleLogBook_db.model;


import com.glucoclock.database.Logbook;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Simplelogbook_db")
public class SimpleLogBook extends Logbook implements Comparable<SimpleLogBook> {





    public SimpleLogBook(UUID PatientUid, LocalDate Date, String TimeString, String BloodGlucose, String CarbIntake) {

        patientuid = PatientUid;
        date = Date;
        bloodglucose = BloodGlucose;
        carbintake = CarbIntake;
        if (TimeString.equals("PreBreakfast")) time =1;
        else if (TimeString.equals("PostBreakfast")) time =2;
        else if (TimeString.equals("PreLunch")) time =3;
        else if (TimeString.equals("PostLunch")) time =4;
        else if (TimeString.equals("PreDinner")) time =5;
        else if (TimeString.equals("PostDinner")) time =6;

    }

    public SimpleLogBook() {

    }

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
    public int compareTo(SimpleLogBook that){
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
        return "Simple" +
                "," + date +
                "," + this.getTimeString() +
                "," + bloodglucose +
                "," + carbintake +
                "," + "-"+
                "," + "-" +
                "," + "-" +
                "," + "-" +
                "," + "-";
    }

}


