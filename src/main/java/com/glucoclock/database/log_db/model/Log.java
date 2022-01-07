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

    //Columns of the database and variables
    //Record date
    @Column (name="Date")
    private LocalDate date;

    //Record logbook type
    @Column (name="LogBookType")
    private int logbooktype;

    //Record patient uid
    @Column (name="Patient_uid")
    private UUID patientuid;


//  Constructor
    public Log(UUID patientuid,LocalDate date, int logbooktype){
        this.patientuid=patientuid;
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
    //Compare record date of logs
    public int compareTo(Log that) {
        return this.date.compareTo(that.date);
    }


}
