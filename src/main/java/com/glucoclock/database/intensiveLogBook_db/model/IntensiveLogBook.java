package com.glucoclock.database.intensiveLogBook_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Intensivelogbook_db")
public class IntensiveLogBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "PatientId")
    private Long patientid;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name="Time")
    private String time;

    @Column(name = "BloodGlucose")
    private String bloodglucose;

    @Column(name = "CarbIntake")
    private String carbintake;

    @Column(name="InsulinDose")
    private String insulindose;

    @Column (name="Food")
    private String food;

    @Column (name="ExerciseDuration")
    private String exerciseduration;

    @Column (name="ExerciseType")
    private String exercisetype;

    @Column (name="UnusualEvent")
    private String unusualevent;



    public IntensiveLogBook(Long PatientId, LocalDate Date, String Time, String BloodGlucose, String CarbIntake, String InsulinDose, String Food, String ExerciseDur, String ExerciseType, String UnusualEvent) {

        patientid = PatientId;
        date = Date;
        bloodglucose = BloodGlucose;
        carbintake = CarbIntake;
        time=Time;
        insulindose=InsulinDose;
        food=Food;
        exerciseduration=ExerciseDur;
        exercisetype=ExerciseType;
        unusualevent=UnusualEvent;
    }

    public IntensiveLogBook() {

    }


    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getExerciseduration() {
        return exerciseduration;
    }

    public void setExerciseduration(String exerciseduration) {
        this.exerciseduration = exerciseduration;
    }

    public String getExercisetype() {
        return exercisetype;
    }

    public void setExercisetype(String exercisetype) {
        this.exercisetype = exercisetype;
    }

    public String getUnusualevent() {
        return unusualevent;
    }

    public void setUnusualevent(String unusualevent) {
        this.unusualevent = unusualevent;
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
}