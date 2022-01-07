package com.glucoclock.database.log_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    private Log l = new Log();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void createLogbook () {
        l.setDate(LocalDate.of(2022,1,1));
        l.setLogbooktype(1);
        l.setId(1L);
        //l.setTime(1);
        l.setPatientuid(uid);
    }
/*
    @Test
    void getTime() {
        assertEquals(1,l.getTime());
    }

 */

    @Test
    void getId() {
        assertEquals(1L,l.getId());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2022,1,1),l.getDate());
    }

    @Test
    void getLogbooktype() {
        assertEquals(1,l.getLogbooktype());
    }

    @Test
    void getPatientuid() {
        assertEquals(uid,l.getPatientuid());
    }

}