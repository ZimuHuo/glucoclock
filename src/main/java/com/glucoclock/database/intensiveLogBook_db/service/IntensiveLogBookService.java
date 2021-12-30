package com.glucoclock.database.intensiveLogBook_db.service;

import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.repository.IntensiveLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class IntensiveLogBookService {
    @Autowired
    IntensiveLogBookRepository repository;

    public String bulkcreate(){
        LocalDate test= LocalDate.now();
        //Intensive: patient id, date, blood glucose, carb intake, food, exercise duration, exercise type, unusual event
        repository.save(new IntensiveLogBook(UUID.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),test.minusDays(1), LocalTime.of(9,0,0),"23","32","33","13","35","24","45"));
        repository.save(new IntensiveLogBook(UUID.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),test.minusDays(1), LocalTime.of(17,0,0),"23","34","33","23","35","24","56"));
        repository.save(new IntensiveLogBook(UUID.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),test.minusDays(1),LocalTime.of(14,0,0),"23","33","21","34","24","25","45"));
        repository.save(new IntensiveLogBook(UUID.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),test.minusDays(1),LocalTime.of(5,0,0),"23","32","22","24","35","35","34"));
        return "Intensive Log is created";
    }
    public String create(IntensiveLogBook IntensiveLogBook){
        repository.save(new IntensiveLogBook(IntensiveLogBook.getPatientuid(),IntensiveLogBook.getDate(),IntensiveLogBook.getTime(),IntensiveLogBook.getBloodglucose(),IntensiveLogBook.getCarbintake(),IntensiveLogBook.getInsulindose(),IntensiveLogBook.getCarbbolus(),IntensiveLogBook.getHighbsbolus(),IntensiveLogBook.getBasalrate(),IntensiveLogBook.getKetons()));
        return "Intensive Log is created";
    }

    public List<IntensiveLogBook> findAll(){
        List<IntensiveLogBook> IntensiveLogBooks=repository.findAll();
        List<IntensiveLogBook>IntensiveLogBookObjects=new ArrayList<>();
        for(IntensiveLogBook IntensiveLog:IntensiveLogBooks){
            IntensiveLogBookObjects.add(new IntensiveLogBook(IntensiveLog.getPatientuid(),IntensiveLog.getDate(),IntensiveLog.getTime(),IntensiveLog.getBloodglucose(),IntensiveLog.getCarbintake(),IntensiveLog.getInsulindose(),IntensiveLog.getCarbbolus(),IntensiveLog.getHighbsbolus(),IntensiveLog.getBasalrate(),IntensiveLog.getKetons()));
        }
        return IntensiveLogBookObjects;
    }

    //-->list of simple log of one patient
    public List<IntensiveLogBook> findLogsByPatientid(UUID patient_uid){
        List<IntensiveLogBook> Intensivelog;
        Intensivelog=repository.findByPatientuid(patient_uid);
        Collections.sort(Intensivelog);
        return Intensivelog;
    }

    //find one log--> using date and patient id
    public List<IntensiveLogBook> findLogByDateAndPatientuid(LocalDate date,UUID patient_uid){
        List<IntensiveLogBook> Intensivelog;
        Intensivelog=repository.findByPatientuidAndAndDate(patient_uid,date);
        Collections.sort(Intensivelog);
        return Intensivelog;
    }

    public IntensiveLogBookRepository getRepository(){
        return repository;
    }

    //find using date and patient id
    //the patient can reset blood glucose and carb intake
    // date and patient id should only upload at the time they create a new log
    public void updateBloodGlucose(UUID patientuid,LocalDate Date,String Time,String BloodGlucose){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setBloodglucose(BloodGlucose);
        repository.save(Intensivelog);
    }

    public void updateCarbIntake(UUID patientuid,LocalDate Date,String Time,String CarbIntake){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setCarbintake(CarbIntake);
        repository.save(Intensivelog);
    }

    public void updateInsulinDose(UUID patientuid,LocalDate Date,String Time,String InsulinDose){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setInsulindose(InsulinDose);
        repository.save(Intensivelog);
    }

    public void updateCarbBolus(UUID patientuid,LocalDate Date,String Time,String CarbBolus){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setCarbbolus(CarbBolus);
        repository.save(Intensivelog);
    }

    public void updateHighBSBolus(UUID patientuid,LocalDate Date,String Time,String HighBSBolus){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setHighbsbolus(HighBSBolus);
        repository.save(Intensivelog);
    }

    public void updateBasalRate(UUID patientuid,LocalDate Date,String Time,String BasalRate){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setBasalrate(BasalRate);
        repository.save(Intensivelog);
    }

    public void updateKetones(UUID patientuid,LocalDate Date,String Time,String Ketones){
        IntensiveLogBook Intensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Intensivelog.setKetons(Ketones);
        repository.save(Intensivelog);
    }

}