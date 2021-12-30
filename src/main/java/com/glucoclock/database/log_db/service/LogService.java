package com.glucoclock.database.log_db.service;


import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class LogService {
    @Autowired
    LogRepository repository;

    public String bulkcreate(){
        LocalDate test=LocalDate.now();

        repository.save(new Log(UUID.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),test.minusDays(4),2));
        repository.save(new Log(UUID.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),test.minusDays(1),3));
        repository.save(new Log(UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336"),test.minusDays(8),1));
        repository.save(new Log(UUID.fromString("1e522787-854c-41f6-81b1-cd169b6d3c3d"),test.minusDays(3),1));

        return "Log is created";
    }

    public String create(Log logbook){
        repository.save(new Log(logbook.getPatientuid(),logbook.getDate(),logbook.getLogbooktype()));
        return "Log is created";
    }

    public List<Log> findLogBooksByPatientid(UUID patientuid){
        List<Log> log;
        log=repository.findByPatientuid(patientuid);
        Collections.sort(log);
        return log;
    }

    public List<Log> findLogBooksBetweenDate(LocalDate start, LocalDate end, UUID patientuid){
        List<Log> log;
        log=repository.findByDateBetweenAndPatientuid(start,end,patientuid);
        Collections.sort(log);
        return log;
    }




}
