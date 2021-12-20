package com.glucoclock.database.log_db.repository;

import com.glucoclock.database.log_db.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long> {
    List<Log> findAll();
    List<Log> findByPatientid(long patientid);
    List<Log> findByDateBetweenAndPatientid(LocalDate startdate, LocalDate enddate, long patientid);
    @Override
    <S extends Log> S save(S s);
}
