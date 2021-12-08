package com.glucoclock.database.log_db.model;

import javax.persistence.Column;
import java.time.LocalDate;

public class LogObject {
    private LocalDate date;
    private String logbooktype;
    private Long patientid;

    public LogObject(Long patientid,LocalDate date, String logbooktype){
        this.patientid=patientid;
        this.date=date;
        this.logbooktype=logbooktype;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLogbooktype() {
        return logbooktype;
    }

    public void setLogbooktype(String logbooktype) {
        this.logbooktype = logbooktype;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }
}
