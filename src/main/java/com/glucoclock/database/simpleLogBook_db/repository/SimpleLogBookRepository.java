package com.glucoclock.database.simpleLogBook_db.repository;

import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


// add update delete search
public interface SimpleLogBookRepository extends CrudRepository<SimpleLogBook, Long>{
    List<SimpleLogBook> findAll();
    List<SimpleLogBook> findByPatientuid(UUID patientuid);
    SimpleLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,String Time, LocalDate Date);
    List<SimpleLogBook> findByPatientuidAndAndDate(UUID patient_uid,LocalDate Date);
    @Override
    <S extends SimpleLogBook> S save(S s);
    SimpleLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,int Time, LocalDate Date);

}