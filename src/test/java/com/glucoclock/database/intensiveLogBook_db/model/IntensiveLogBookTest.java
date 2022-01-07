package com.glucoclock.database.intensiveLogBook_db.model;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IntensiveLogBookTest {
    private IntensiveLogBook i = new IntensiveLogBook();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void createIntensiveLogBook(){
        i.setBasalrate("1");
        i.setBloodglucose("123");
        i.setCarbbolus("1");
        i.setCarbintake("120");
        i.setInsulindose("1");
        i.setHighbsbolus("200");
        i.setKetons("1");
        i.setDate(LocalDate.of(2022,1,1));
        i.setId(1L);
        i.setPatientuid(uid);
        i.setTime(LocalTime.of(10,00,00));
    }

    @Test
    void getCarbbolus() {
        assertEquals("1",i.getCarbbolus());
    }

    @Test
    void getHighbsbolus() {
        assertEquals("200",i.getHighbsbolus());
    }

    @Test
    void getBasalrate() {
        assertEquals("1",i.getBasalrate());
    }

    @Test
    void getKetons() {
        assertEquals("1",i.getKetons());
    }

    @Test
    void getInsulindose() {
        assertEquals("1",i.getInsulindose());
    }

    @Test
    void getId() {
        assertEquals(1L,i.getId());
    }

    @Test
    void getPatientuid() {
        assertEquals(uid,i.getPatientuid());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2022,1,1),i.getDate());
    }

    @Test
    void getTime() {
        assertEquals(LocalTime.of(10,00,00),i.getTime());
    }

    @Test
    void getBloodglucose() {
        assertEquals("123",i.getBloodglucose());
    }

    @Test
    void getCarbintake() {
        assertEquals("120",i.getCarbintake());
    }
}