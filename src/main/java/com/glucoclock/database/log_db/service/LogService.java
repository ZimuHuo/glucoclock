package com.glucoclock.database.log_db.service;


import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class LogService {
    @Autowired
    LogRepository repository;

    public String bulkcreate(){
        LocalDate test=LocalDate.now();

        repository.save(new Log(1L,test.minusDays(4),2));
        repository.save(new Log(1L,test.minusDays(1),3));
        repository.save(new Log(1L,test.minusDays(8),1));
        repository.save(new Log(1L,test.minusDays(3),1));

        return "Log is created";
    }

    public String create(Log logbook){
        repository.save(new Log(logbook.getPatientid(),logbook.getDate(),logbook.getLogbooktype()));
        return "Log is created";
    }

    public List<Log> findLogBooksByPatientid(long patientid){
        List<Log> log;
        log=repository.findByPatientid(patientid);
        Collections.sort(log);
        return log;
    }

    public List<Log> findLogBooksBetweenDate(LocalDate start, LocalDate end, long patientid){
        List<Log> log;
        log=repository.findByDateBetweenAndPatientid(start,end,patientid);
        Collections.sort(log);
        return log;
    }




}
