package com.glucoclock.database.intensiveLogBook_db.repository;

import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface IntensiveLogBookRepository extends CrudRepository<IntensiveLogBook, Long> {
//Find list
    List<IntensiveLogBook> findAll();
    //find list of ALL intensive logbooks of a patient
    List<IntensiveLogBook> findByPatientuid(UUID patient_uid);
    //find list of intensive logbooks of a patient at a specific day
    List<IntensiveLogBook> findByPatientuidAndDate(UUID patient_uid,LocalDate Date);
//Find single logbook
    //find an intensive logbook of a patient at specific time and date
    IntensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid,String Time, LocalDate Date);
    IntensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid, LocalTime time, LocalDate date);
    IntensiveLogBook findByPatientuidAndTimeAndDate(UUID patient_uid, int time, LocalDate date);
    @Override
    <S extends IntensiveLogBook> S save(S s);

}