package com.glucoclock.database.comprehensiveLogBook_db.service;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.repository.ComprehensiveLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ComprehensiveLogBookService {
    @Autowired
    ComprehensiveLogBookRepository repository;

    public String bulkcreate(){
        LocalDate test= LocalDate.now();
        //Comprehensive: patient id, date, blood glucose, carb intake
        repository.save(new ComprehensiveLogBook(UUID.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),test.minusDays(4),"PreBreakfast","23","32","33"));
        repository.save(new ComprehensiveLogBook(UUID.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),test.minusDays(4),"PostLunch","23","34","33"));
        repository.save(new ComprehensiveLogBook(UUID.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),test.minusDays(4),"PreLunch","23","33","21"));
        repository.save(new ComprehensiveLogBook(UUID.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),test.minusDays(4),"PostBreakfast","23","32","22"));
       return "Comprehensive Log is created";
    }
    public String create(ComprehensiveLogBook ComprehensiveLogBook){
        repository.save(new ComprehensiveLogBook(ComprehensiveLogBook.getPatientuid(),ComprehensiveLogBook.getDate(),ComprehensiveLogBook.getTimeString(),ComprehensiveLogBook.getBloodglucose(),ComprehensiveLogBook.getCarbintake(),ComprehensiveLogBook.getInsulindose()));
        return "Comprehensive Log is created";
    }

    public List<ComprehensiveLogBook> findAll(){
        List<ComprehensiveLogBook> ComprehensiveLogBooks=repository.findAll();
        List<ComprehensiveLogBook>ComprehensiveLogBookObjects=new ArrayList<>();
        for(ComprehensiveLogBook ComprehensiveLog:ComprehensiveLogBooks){
            ComprehensiveLogBookObjects.add(new ComprehensiveLogBook(ComprehensiveLog.getPatientuid(),ComprehensiveLog.getDate(),ComprehensiveLog.getTimeString(),ComprehensiveLog.getBloodglucose(),ComprehensiveLog.getCarbintake(),ComprehensiveLog.getInsulindose()));
        }
        return ComprehensiveLogBookObjects;
    }

    //Find list of ALL comprehensive logs of one patient
    public List<ComprehensiveLogBook> findLogsByPatientuid(UUID patient_uid){
        List<ComprehensiveLogBook> Comprehensivelog;
        Comprehensivelog=repository.findByPatientuid(patient_uid);
        Collections.sort(Comprehensivelog);
        return Comprehensivelog;
    }

    //Find list of logs for a patient at a specific day
    public List<ComprehensiveLogBook> findLogByDateAndPatientuid(LocalDate date,UUID patient_uid){
        List<ComprehensiveLogBook> Comprehensivelog;
        Comprehensivelog=repository.findByPatientuidAndDate(patient_uid,date);
        Collections.sort(Comprehensivelog);
        return Comprehensivelog;
    }

    //Get repository
    public ComprehensiveLogBookRepository getRepository(){
        return repository;
    }

    //find using date and patient id
    //the patient can reset blood glucose and carb intake
    // date and patient id should only upload at the time they create a new log
    public void updateBloodGlucose(UUID patientuid,LocalDate Date,Integer Time,String BloodGlucose){
        ComprehensiveLogBook Comprehensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Comprehensivelog.setBloodglucose(BloodGlucose);
        repository.save(Comprehensivelog);
    }

    public void updateCarbIntake(UUID patientuid,LocalDate Date,Integer Time,String CarbIntake){
        ComprehensiveLogBook Comprehensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Comprehensivelog.setCarbintake(CarbIntake);
        repository.save(Comprehensivelog);
    }

    public void updateInsulinDose(UUID patientuid,LocalDate Date,Integer Time,String InsulinDose){
        ComprehensiveLogBook Comprehensivelog=repository.findByPatientuidAndTimeAndDate(patientuid,Time,Date);
        Comprehensivelog.setInsulindose(InsulinDose);
        repository.save(Comprehensivelog);
    }

}