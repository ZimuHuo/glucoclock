package com.glucoclock.database.simpleLogBook_db.repository;

import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


// add update delete search
public interface SimpleLogBookRepository extends CrudRepository<SimpleLogBook, Long>{
//Find list
    List<SimpleLogBook> findAll();
    //find list of ALL simple logbooks of a patient
    List<SimpleLogBook> findByPatientuid(UUID patientuid);
    //find list of comprehensive logbooks of a patient at a specific day
    List<SimpleLogBook> findByPatientuidAndDate(UUID patient_uid,LocalDate Date);
//Find single logbook
    //find a comprehensive logbook of a patient at specific time and date
    SimpleLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,String Time, LocalDate Date);

    @Override
    <S extends SimpleLogBook> S save(S s);
    SimpleLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,int Time, LocalDate Date);

}