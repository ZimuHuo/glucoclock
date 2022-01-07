package com.glucoclock.database.questionnaire_db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireTest {
    private Questionnaire q = new Questionnaire();
    LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void createQuestionnaire () {
        q.setDateTime(now);
        q.setOtherSymptoms("xxx");
        q.setSymptoms("sss");
    }

    @Test
    void getDateTime() {
        assertEquals(now,q.getDateTime());
    }

    @Test
    void getSymptoms() {
        assertEquals("sss",q.getSymptoms());
    }

    @Test
    void getOtherSymptoms() {
        assertEquals("xxx",q.getOtherSymptoms());
    }
}