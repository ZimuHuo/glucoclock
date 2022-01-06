package com.glucoclock.database.comprehensiveLogBook_db.repository;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ComprehensiveLogBookRepository extends CrudRepository<ComprehensiveLogBook, Long>{
    List<ComprehensiveLogBook> findAll();
    List<ComprehensiveLogBook> findByPatientuid(UUID patient_uid);
    ComprehensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,String Time, LocalDate Date);
    List<ComprehensiveLogBook> findByPatientuidAndAndDate(UUID patient_uid,LocalDate Date);
    @Override
    <S extends ComprehensiveLogBook> S save(S s);
    ComprehensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,int Time, LocalDate Date);
}
