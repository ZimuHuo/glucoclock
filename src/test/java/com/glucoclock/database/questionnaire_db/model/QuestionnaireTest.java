package com.glucoclock.database.questionnaire_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireTest {

    UUID uid = UUID.randomUUID();
    LocalDateTime now = LocalDateTime.now();
    private Questionnaire q = new Questionnaire(uid,now,
            "s","o");


    @BeforeEach
    void createQuestionnaire () {
        q.setDateTime(now);


    }

    @Test
    void getDateTime() {
        assertEquals(now,q.getDateTime());
    }

    @Test
    void getSymptoms() {
        assertEquals("s",q.getSymptoms());
    }

    @Test
    void getOtherSymptoms() {
        assertEquals("o",q.getOtherSymptoms());
    }

    @Test
    void setDateTime() {
        q.setDateTime(LocalDateTime.of(2000,1,1,12,00));
        assertEquals(LocalDateTime.of(2000,1,1,12,00),q.getDateTime());
    }

    @Test
    void setSymptoms() {
        q.setSymptoms("sss");
        assertEquals("sss",q.getSymptoms());
    }

    @Test
    void setOtherSymptoms() {
        q.setOtherSymptoms("xxx");
        assertEquals("xxx",q.getOtherSymptoms());
    }


}