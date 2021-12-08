package com.glucoclock.database.intensiveLogBook_db.service;

import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBookObject;
import com.glucoclock.database.intensiveLogBook_db.repository.IntensiveLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class IntensiveLogBookService {
    @Autowired
    IntensiveLogBookRepository repository;

    public String bulkcreate(){
        LocalDate test= LocalDate.now();
        //Intensive: patient id, date, blood glucose, carb intake, food, exercise duration, exercise type, unusual event
        repository.save(new IntensiveLogBook(1L,test,"9 AM","23","32","33","bread","30min","run","no"));
        repository.save(new IntensiveLogBook(1L,test,"12 AM","23","34","33","rice","20min","run","no"));
        repository.save(new IntensiveLogBook(1L,test,"2 PM","23","33","21","-","12min","run","no"));
        repository.save(new IntensiveLogBook(1L,test,"5 PM","23","32","22","rice","40min","run","no"));
        return "Intensive Log is created";
    }
    public String create(IntensiveLogBookObject IntensiveLogBook){
        repository.save(new IntensiveLogBook(IntensiveLogBook.getPatientId(),IntensiveLogBook.getDate(),IntensiveLogBook.getTime(),IntensiveLogBook.getBloodGlucose(),IntensiveLogBook.getCarbIntake(),IntensiveLogBook.getInsulinDose(),IntensiveLogBook.getFood(),IntensiveLogBook.getExerciseduration(),IntensiveLogBook.getExercisetype(),IntensiveLogBook.getUnusualevent()));
        return "Intensive Log is created";
    }

    public List<IntensiveLogBookObject> findAll(){
        List<IntensiveLogBook> IntensiveLogBooks=repository.findAll();
        List<IntensiveLogBookObject>IntensiveLogBookObjects=new ArrayList<>();
        for(IntensiveLogBook IntensiveLog:IntensiveLogBooks){
            IntensiveLogBookObjects.add(new IntensiveLogBookObject(IntensiveLog.getPatientid(),IntensiveLog.getDate(),IntensiveLog.getTime(),IntensiveLog.getBloodglucose(),IntensiveLog.getCarbintake(),IntensiveLog.getInsulindose(),IntensiveLog.getFood(),IntensiveLog.getExerciseduration(),IntensiveLog.getExercisetype(),IntensiveLog.getUnusualevent()));
        }
        return IntensiveLogBookObjects;
    }

    //-->list of simple log of one patient
    public List<IntensiveLogBook> findLogsByPatientid(long patient_id){
        List<IntensiveLogBook> Intensivelog;
        Intensivelog=repository.findByPatientid(patient_id);
        return Intensivelog;
    }

    //find one log--> using date and patient id
    public List<IntensiveLogBook> findLogByDateAndPatientid(LocalDate date,long patient_id){
        List<IntensiveLogBook> Intensivelog;
        Intensivelog=repository.findByPatientidAndAndDate(patient_id,date);
        return Intensivelog;
    }

    public IntensiveLogBookRepository getRepository(){
        return repository;
    }

    //find using date and patient id
    //the patient can reset blood glucose and carb intake
    // date and patient id should only upload at the time they create a new log
    public void updateBloodGlucose(long patientid,LocalDate Date,String Time,String BloodGlucose){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setBloodglucose(BloodGlucose);
        repository.save(Intensivelog);
    }

    public void updateCarbIntake(long patientid,LocalDate Date,String Time,String CarbIntake){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setCarbintake(CarbIntake);
        repository.save(Intensivelog);
    }

    public void updateInsulinDose(long patientid,LocalDate Date,String Time,String InsulinDose){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setInsulindose(InsulinDose);
        repository.save(Intensivelog);
    }

    public void updateFood(long patientid,LocalDate Date,String Time,String Food){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setFood(Food);
        repository.save(Intensivelog);
    }

    public void updateExerciseDuration(long patientid,LocalDate Date,String Time,String ExerciseDur){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setExerciseduration(ExerciseDur);
        repository.save(Intensivelog);
    }

    public void updateExerciseType(long patientid,LocalDate Date,String Time,String ExerciseType){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setExercisetype(ExerciseType);
        repository.save(Intensivelog);
    }

    public void updateUnusualEvent(long patientid,LocalDate Date,String Time,String UnusualEvent){
        IntensiveLogBook Intensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Intensivelog.setUnusualevent(UnusualEvent);
        repository.save(Intensivelog);
    }

}