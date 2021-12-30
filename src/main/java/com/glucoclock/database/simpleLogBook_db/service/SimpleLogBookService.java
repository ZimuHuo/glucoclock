package com.glucoclock.database.simpleLogBook_db.service;

import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.repository.SimpleLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class SimpleLogBookService {
    @Autowired
    SimpleLogBookRepository repository;

    public String bulkcreate(){
        LocalDate test= LocalDate.now();
        //Simple: patient id, date, blood glucose, carb intake
        repository.save(new SimpleLogBook(UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336"),test.minusDays(8),1,"23","32"));
        repository.save(new SimpleLogBook(UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336"),test.minusDays(8),6,"23","34"));
        repository.save(new SimpleLogBook(UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336"),test.minusDays(8),3,"23","33"));
        repository.save(new SimpleLogBook(UUID.fromString("1e522787-854c-41f6-81b1-cd169b6d3c3d"),test.minusDays(3),4,"23","32"));
        return "Simple Log is created";
    }
    public String create(SimpleLogBook SimpleLogBook){
        repository.save(new SimpleLogBook(SimpleLogBook.getPatientuid(),SimpleLogBook.getDate(),SimpleLogBook.getTime(),SimpleLogBook.getBloodglucose(),SimpleLogBook.getCarbintake()));
        return "Simple Log is created";
    }

    public List<SimpleLogBook> findAll(){
        List<SimpleLogBook> SimpleLogBooks=repository.findAll();
        List<SimpleLogBook>SimpleLogBookObjects=new ArrayList<>();
        for(SimpleLogBook SimpleLog:SimpleLogBooks){
            SimpleLogBookObjects.add(new SimpleLogBook(SimpleLog.getPatientuid(),SimpleLog.getDate(),SimpleLog.getTime(),SimpleLog.getBloodglucose(),SimpleLog.getCarbintake()));
        }
        return SimpleLogBookObjects;
    }

    //-->list of simple log of one patient
    public List<SimpleLogBook> findLogsByPatientid(UUID patient_uid){
        List<SimpleLogBook> SimpleLog;
        SimpleLog=repository.findByPatientuid(patient_uid);
        return SimpleLog;
    }

    //find one log--> using date and patient id
    public List<SimpleLogBook> findLogByDateAndPatientuid(LocalDate date, UUID patient_uid){
        List<SimpleLogBook> SimpleLog;
        SimpleLog=repository.findByPatientuidAndAndDate(patient_uid,date);
        Collections.sort(SimpleLog);
        return SimpleLog;
    }

    public SimpleLogBookRepository getRepository(){
        return repository;
    }

//find using date and patient id
    //the patient can reset blood glucose and carb intake
    // date and patient id should only upload at the time they create a new log
    public void updateBloodGlucose(UUID patientuid,LocalDate Date,String Time,String BloodGlucose){
        SimpleLogBook SimpleLog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        SimpleLog.setBloodglucose(BloodGlucose);
        repository.save(SimpleLog);
    }

    public void updateCarbIntake(UUID patientuid,LocalDate Date,String Time,String CarbIntake){
        SimpleLogBook SimpleLog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        SimpleLog.setCarbintake(CarbIntake);
        repository.save(SimpleLog);
    }

}
