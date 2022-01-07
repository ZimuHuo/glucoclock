package com.glucoclock.database.log_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    UUID uid = UUID.randomUUID();
    UUID uid2 = UUID.randomUUID();
    private Log l = new Log(uid,LocalDate.of(2000,1,1),
            2,2);


    @Test
    void getTime() {
        assertEquals(2,l.getTime());
    }
/*
    @Test
    void getDate() {
        assertEquals(LocalDate.of(2000,1,1),l.getDate());
    }

    @Test
    void getLogbooktype() {
        assertEquals(2,l.getLogbooktype());
    }

    @Test
    void getPatientuid() {
        l.setPatientuid(uid2);
        assertEquals(uid2,l.getPatientuid());
    }

    @Test
    void setTime() {
        l.setTime(1);
        assertEquals(1,l.getTime());
    }

 */

    @Test
    void setId() {
        l.setId(1L);
        assertEquals(1L,l.getId());
    }

    @Test
    void setDate() {
        l.setDate(LocalDate.of(2022,1,1));
        assertEquals(LocalDate.of(2022,1,1),l.getDate());
    }

    @Test
    void setLogbooktype() {
        l.setLogbooktype(1);
        assertEquals(1,l.getLogbooktype());
    }

    @Test
    void setPatientuid() {
        assertEquals(uid,l.getPatientuid());
    }
}