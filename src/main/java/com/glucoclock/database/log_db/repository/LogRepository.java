package com.glucoclock.database.log_db.repository;

import com.glucoclock.database.log_db.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LogRepository extends CrudRepository<Log, Long> {
    //Find list
    List<Log> findAll();
    //find list of ALL logbooks of a patient
    List<Log> findByPatientuid(UUID patientid);
    //find list of comprehensive logbooks of a patient between 2 days
    List<Log> findByDateBetweenAndPatientuid(LocalDate startdate, LocalDate enddate, UUID patientuid);
    //Find single logbook
    //find a logbook at a specific date for a patient
    Log findByDateAndPatientuid(LocalDate date, UUID patientuid);
    @Override
    <S extends Log> S save(S s);
}
