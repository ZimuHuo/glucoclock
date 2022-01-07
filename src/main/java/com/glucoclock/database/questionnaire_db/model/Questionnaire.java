package com.glucoclock.database.questionnaire_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Questionnaire_db")
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="uid")
    private UUID uid;

    @Column(name="DateTime")
    private LocalDateTime dateTime;

    @Column(name="Symptoms")
    private String symptoms;

    @Column(name="OtherSymptoms")
    private String otherSymptoms;



    public Questionnaire(UUID uid,
                         LocalDateTime dateTime,
                         String symptoms,
                         String otherSymptoms){
        this.uid = uid;
        this.dateTime = dateTime;
        this.symptoms = symptoms;
        this.otherSymptoms = otherSymptoms;
    };

    public Questionnaire(){};

    public UUID getUid() {
        return uid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getOtherSymptoms() {
        return otherSymptoms;
    }
    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }
}



