package com.glucoclock.database.simpleLogBook_db.service;

import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBookObject;
import com.glucoclock.database.simpleLogBook_db.repository.SimpleLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleLogBookService {
    @Autowired
    SimpleLogBookRepository repository;

    public String bulkcreate(){
        LocalDate test= LocalDate.now();
        //Simple: patient id, date, blood glucose, carb intake
        repository.save(new SimpleLogBook(1L,test,"30","23","Pre Breakfast"));
        repository.save(new SimpleLogBook(1L,test,"30","23","Pre Lunch"));
        repository.save(new SimpleLogBook(1L,test,"30","23","After Lunch"));
        repository.save(new SimpleLogBook(1L,test,"30","23","Pre Dinner"));
        return "Simple Log is created";
    }
    public String create(SimpleLogBookObject SimpleLogBook){
        repository.save(new SimpleLogBook(SimpleLogBook.getPatientId(),SimpleLogBook.getDate(),SimpleLogBook.getBloodGlucose(),SimpleLogBook.getCarbIntake(),SimpleLogBook.getTime()));
        return "Simple Log is created";
    }

    public List<SimpleLogBookObject> findAll(){
        List<SimpleLogBook> SimpleLogBooks=repository.findAll();
        List<SimpleLogBookObject>SimpleLogBookObjects=new ArrayList<>();
        for(SimpleLogBook SimpleLog:SimpleLogBooks){
            SimpleLogBookObjects.add(new SimpleLogBookObject(SimpleLog.getPatientid(),SimpleLog.getDate(),SimpleLog.getBloodglucose(),SimpleLog.getCarbintake(),SimpleLog.getTime()));
        }
        return SimpleLogBookObjects;
    }

    //-->list of simple log of one patient
    public List<SimpleLogBook> findLogsByPatientid(long patient_id){
        List<SimpleLogBook> SimpleLog;
        SimpleLog=repository.findByPatientid(patient_id);
        return SimpleLog;
    }

    //find one log--> using date and patient id
    public List<SimpleLogBook> findLogByDateAndPatientid(LocalDate date,long patient_id){
        List<SimpleLogBook> SimpleLog;
        SimpleLog=repository.findByPatientidAndAndDate(patient_id,date);
        return SimpleLog;
    }

    public SimpleLogBookRepository getRepository(){
        return repository;
    }

//find using date and patient id
    //the patient can reset blood glucose and carb intake
    // date and patient id should only upload at the time they create a new log
    public void updateBloodGlucose(long patientid,LocalDate Date,String Time,String BloodGlucose){
        SimpleLogBook SimpleLog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        SimpleLog.setBloodglucose(BloodGlucose);
        repository.save(SimpleLog);
    }

    public void updateCarbIntake(long patientid,LocalDate Date,String Time,String CarbIntake){
        SimpleLogBook SimpleLog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        SimpleLog.setCarbintake(CarbIntake);
        repository.save(SimpleLog);
    }

}
