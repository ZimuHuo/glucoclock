package com.glucoclock.database.intensiveLogBook_db.repository;

import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IntensiveLogBookRepository extends CrudRepository<IntensiveLogBook, Long> {
    List<IntensiveLogBook> findAll();
    List<IntensiveLogBook> findByPatientuid(UUID patient_uid);
    IntensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,String Time, LocalDate Date);
    List<IntensiveLogBook> findByPatientuidAndAndDate(UUID patient_uid,LocalDate Date);
    @Override
    <S extends IntensiveLogBook> S save(S s);

}