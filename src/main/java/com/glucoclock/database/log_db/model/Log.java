package com.glucoclock.database.log_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table (name="Log_db")
public class Log implements Serializable, Comparable<Log> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name="Date")
    private LocalDate date;

    @Column (name="LogBookType")
    private int logbooktype;

    @Column (name="Patient_id")
    private Long patientid;

    public Log(Long patientid,LocalDate date, int logbooktype){
        this.patientid=patientid;
        this.date=date;
        this.logbooktype=logbooktype;

    }

    public Log() {

    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", date=" + date +
                ", logbooktype='" + logbooktype + '\'' +
                ", patientid=" + patientid +
                '}';
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

    public int getLogbooktype() {
        return logbooktype;
    }

    public void setLogbooktype(int logbooktype) {
        this.logbooktype = logbooktype;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public String getStringLogBooktype(){
        String returnLogbooktype="none";
        if(logbooktype==1) returnLogbooktype="Simple";
        if(logbooktype==2) returnLogbooktype="Comprehensive";
        if(logbooktype==3) returnLogbooktype="Intensive";
        return returnLogbooktype;
    }
    @Override
    public int compareTo(Log that) {
        return this.date.compareTo(that.date);
    }


}
