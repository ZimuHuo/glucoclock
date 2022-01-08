package com.glucoclock.database.comprehensiveLogBook_db.repository;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ComprehensiveLogBookRepository extends CrudRepository<ComprehensiveLogBook, Long>{
//Find list
    List<ComprehensiveLogBook> findAll();
    //find list of ALL comprehensive logbooks of a patient
    List<ComprehensiveLogBook> findByPatientuid(UUID patient_uid);
    //find list of comprehensive logbooks of a patient at a specific day
    List<ComprehensiveLogBook> findByPatientuidAndDate(UUID patient_uid,LocalDate Date);
//Find single logbook
    //find a comprehensive logbook of a patient at specific time and date
    ComprehensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,String Time, LocalDate Date);

    @Override
    <S extends ComprehensiveLogBook> S save(S s);
    ComprehensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,int Time, LocalDate Date);
}
