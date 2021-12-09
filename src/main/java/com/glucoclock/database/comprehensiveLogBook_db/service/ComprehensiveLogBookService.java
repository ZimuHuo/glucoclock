package com.glucoclock.database.comprehensiveLogBook_db.service;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
//import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBookObject;
import com.glucoclock.database.comprehensiveLogBook_db.repository.ComprehensiveLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComprehensiveLogBookService {
    @Autowired
    ComprehensiveLogBookRepository repository;

    public String bulkcreate(){
        LocalDate test= LocalDate.now();
        //Simple: patient id, date, blood glucose, carb intake
        repository.save(new ComprehensiveLogBook(1L,test,"Pre Breakfast","23","32","33"));
        repository.save(new ComprehensiveLogBook(1L,test,"Pre Lunch","23","34","33"));
        repository.save(new ComprehensiveLogBook(1L,test,"Post Lunch","23","33","21"));
        repository.save(new ComprehensiveLogBook(1L,test,"Pre Dinner","23","32","22"));
        return "Comprehensive Log is created";
    }
    public String create(ComprehensiveLogBook ComprehensiveLogBook){
        repository.save(new ComprehensiveLogBook(ComprehensiveLogBook.getPatientid(),ComprehensiveLogBook.getDate(),ComprehensiveLogBook.getTime(),ComprehensiveLogBook.getBloodglucose(),ComprehensiveLogBook.getCarbintake(),ComprehensiveLogBook.getInsulindose()));
        return "Comprehensive Log is created";
    }

    public List<ComprehensiveLogBook> findAll(){
        List<ComprehensiveLogBook> ComprehensiveLogBooks=repository.findAll();
        List<ComprehensiveLogBook>ComprehensiveLogBookObjects=new ArrayList<>();
        for(ComprehensiveLogBook ComprehensiveLog:ComprehensiveLogBooks){
            ComprehensiveLogBookObjects.add(new ComprehensiveLogBook(ComprehensiveLog.getPatientid(),ComprehensiveLog.getDate(),ComprehensiveLog.getTime(),ComprehensiveLog.getBloodglucose(),ComprehensiveLog.getCarbintake(),ComprehensiveLog.getInsulindose()));
        }
        return ComprehensiveLogBookObjects;
    }

    //-->list of simple log of one patient
    public List<ComprehensiveLogBook> findLogsByPatientid(long patient_id){
        List<ComprehensiveLogBook> Comprehensivelog;
        Comprehensivelog=repository.findByPatientid(patient_id);
        return Comprehensivelog;
    }

    //find one log--> using date and patient id
    public List<ComprehensiveLogBook> findLogByDateAndPatientid(LocalDate date,long patient_id){
        List<ComprehensiveLogBook> Comprehensivelog;
        Comprehensivelog=repository.findByPatientidAndAndDate(patient_id,date);
        return Comprehensivelog;
    }

    public ComprehensiveLogBookRepository getRepository(){
        return repository;
    }

    //find using date and patient id
    //the patient can reset blood glucose and carb intake
    // date and patient id should only upload at the time they create a new log
    public void updateBloodGlucose(long patientid,LocalDate Date,String Time,String BloodGlucose){
        ComprehensiveLogBook Comprehensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Comprehensivelog.setBloodglucose(BloodGlucose);
        repository.save(Comprehensivelog);
    }

    public void updateCarbIntake(long patientid,LocalDate Date,String Time,String CarbIntake){
        ComprehensiveLogBook Comprehensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Comprehensivelog.setCarbintake(CarbIntake);
        repository.save(Comprehensivelog);
    }

    public void updateInsulinDose(long patientid,LocalDate Date,String Time,String InsulinDose){
        ComprehensiveLogBook Comprehensivelog=repository.findByPatientidAndTimeAndDate(patientid,Time,Date);
        Comprehensivelog.setInsulindose(InsulinDose);
        repository.save(Comprehensivelog);
    }

}