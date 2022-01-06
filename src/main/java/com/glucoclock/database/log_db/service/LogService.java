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

    public LogRepository getRepository() {
        return repository;
    }


}
