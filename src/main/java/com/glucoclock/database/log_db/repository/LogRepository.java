package com.glucoclock.database.log_db.repository;

import com.glucoclock.database.log_db.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LogRepository extends CrudRepository<Log, Long> {
    List<Log> findAll();
    List<Log> findByPatientuid(UUID patientid);
    List<Log> findByDateBetweenAndPatientuid(LocalDate startdate, LocalDate enddate, UUID patientuid);
    @Override
    <S extends Log> S save(S s);
}
