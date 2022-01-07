package com.glucoclock.database.simpleLogBook_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLogBookTest {
    UUID uid = UUID.randomUUID();
    private SimpleLogBook s = new SimpleLogBook(uid,
            LocalDate.of(2000,1,1),
            "66",
            "100",
            "101");




    @Test
    void getPatientuid() {
        assertEquals(uid,s.getPatientuid());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2000,1,1),s.getDate());
    }

    @Test
    void getBloodglucose() {
        assertEquals("100",s.getBloodglucose());
    }

    @Test
    void getCarbintake() {
        assertEquals("101",s.getCarbintake());
    }

    @Test
    void setId() {
        s.setId(1L);assertEquals(1L,s.getId());
    }

    @Test
    void setPatientuid() {
        s.setPatientuid(uid);assertEquals(uid,s.getPatientuid());
    }

    @Test
    void setDate() {
        s.setDate(LocalDate.now());assertEquals(LocalDate.now(),s.getDate());
    }

    @Test
    void setTime() {
        s.setTime(1);assertEquals(1,s.getTime());
    }

    @Test
    void setBloodglucose() {
        s.setBloodglucose("123");assertEquals("123",s.getBloodglucose());
    }

    @Test
    void setCarbintake() {
        s.setCarbintake("120");assertEquals("120",s.getCarbintake());
    }


}