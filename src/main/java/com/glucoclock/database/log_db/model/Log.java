package com.glucoclock.database.log_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

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

    @Column (name="Patient_uid")
    private UUID patientuid;
    @Column (name="Time")
    private int time;


    public Log(UUID patientuid,LocalDate date, int logbooktype, int time){
        this.patientuid=patientuid;
        this.date=date;
        this.logbooktype=logbooktype;
this.time = time;
    }

    public Log() {

    }
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", date=" + date +
                ", logbooktype='" + logbooktype + '\'' +
                ", patientid=" + patientuid +
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

    public UUID getPatientuid() {
        return patientuid;
    }

    public void setPatientuid(UUID patientuid) {
        this.patientuid = patientuid;
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
