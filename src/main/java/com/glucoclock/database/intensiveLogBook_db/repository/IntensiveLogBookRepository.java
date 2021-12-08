package com.glucoclock.database.intensiveLogBook_db.repository;

import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface IntensiveLogBookRepository extends CrudRepository<IntensiveLogBook, Long> {
    List<IntensiveLogBook> findAll();
    List<IntensiveLogBook> findByPatientid(Long patient_id);
    IntensiveLogBook findByPatientidAndTimeAndDate(Long patient_id,String Time, LocalDate Date);
    List<IntensiveLogBook> findByPatientidAndAndDate(Long patient_id,LocalDate Date);
    @Override
    <S extends IntensiveLogBook> S save(S s);

}