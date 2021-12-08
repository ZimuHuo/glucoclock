package com.glucoclock.database.intensiveLogBook_db.model;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBookObject;

import java.time.LocalDate;


public class IntensiveLogBookObject extends ComprehensiveLogBookObject {

    private String food;
    private String exerciseduration;
    private String exercisetype;
    private String unusualevent;

    public IntensiveLogBookObject(Long patient_id, LocalDate Date, String Time, String BloodGlucose, String CarbIntake, String InsulinDose, String Food, String ExerciseDur, String ExerciseType, String UnusualEvent) {
        super(patient_id, Date, Time, BloodGlucose, CarbIntake, InsulinDose);
        food=Food;
        exerciseduration=ExerciseDur;
        exercisetype=ExerciseType;
        unusualevent=UnusualEvent;
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

}