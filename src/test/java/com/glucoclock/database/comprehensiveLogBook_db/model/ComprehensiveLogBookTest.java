package com.glucoclock.database.comprehensiveLogBook_db.model;

import com.glucoclock.database.researchers_db.model.Researcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ComprehensiveLogBookTest {
    private ComprehensiveLogBook c = new ComprehensiveLogBook();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void creatComprehensiveLogBook(){
        c.setBloodglucose("123");
        c.setCarbintake("124");
        c.setInsulindose("1");
        c.setDate(LocalDate.of(2022,1,1));
        c.setId(1L);
        c.setPatientuid(uid);
        c.setTime(12);
    }

    @Test
    void getInsulindose() {
        assertEquals("1",c.getInsulindose());
    }

    @Test
    void getId() {
        assertEquals(1L,c.getId());
    }

    @Test
    void getPatientuid() {
        assertEquals(uid,c.getPatientuid());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2022,1,1),c.getDate());
    }

    @Test
    void getTime() {
        assertEquals(12,c.getTime());
    }

    @Test
    void getBloodglucose() {
        assertEquals("123",c.getBloodglucose());
    }

    @Test
    void getCarbintake() {
        assertEquals("124",c.getCarbintake());
    }

}