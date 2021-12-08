package com.glucoclock.database.log_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table (name="Log_db")
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name="Date")
    private LocalDate date;

    @Column (name="LogBookType")
    private String logbooktype;

    @Column (name="Patient_id")
    private Long patientid;

    public Log(Long patientid,LocalDate date, String logbooktype){
        this.patientid=patientid;
        this.date=date;
        this.logbooktype=logbooktype;
    }

    public Log() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
