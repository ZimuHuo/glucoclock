package com.glucoclock.database.simpleLogBook_db.repository;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


// add update delete search
public interface SimpleLogBookRepository extends CrudRepository<SimpleLogBook, Long>{
    List<SimpleLogBook> findAll();
    List<SimpleLogBook> findByPatientid(Long patient_id);
    SimpleLogBook findByPatientidAndTimeAndDate(Long patient_id,String Time, LocalDate Date);
    List<SimpleLogBook> findByPatientidAndAndDate(Long patient_id,LocalDate Date);
    @Override
    <S extends SimpleLogBook> S save(S s);


}