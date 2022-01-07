package com.glucoclock.database.simpleLogBook_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLogBookTest {
    private SimpleLogBook s = new SimpleLogBook();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void createSimpleLogBook() {
        s.setBloodglucose("123");
        s.setCarbintake("120");
        s.setTime(1);
        s.setId(1L);
        s.setPatientuid(uid);
        s.setDate(LocalDate.now());
    }

    @Test
    void getId() {
        assertEquals(1L,s.getId());
    }

    @Test
    void getPatientuid() {
        assertEquals(uid,s.getPatientuid());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.now(),s.getDate());
    }

    @Test
    void getTime() {
        assertEquals(1,s.getTime());
    }

    @Test
    void getBloodglucose() {
        assertEquals("123",s.getBloodglucose());
    }

    @Test
    void getCarbintake() {
        assertEquals("120",s.getCarbintake());
    }


}