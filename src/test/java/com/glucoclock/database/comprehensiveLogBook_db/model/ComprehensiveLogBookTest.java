package com.glucoclock.database.comprehensiveLogBook_db.model;

import com.glucoclock.database.researchers_db.model.Researcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ComprehensiveLogBookTest {
    UUID uid = UUID.randomUUID();
    UUID uid2 = UUID.randomUUID();
    private ComprehensiveLogBook c = new ComprehensiveLogBook(
            uid,
            LocalDate.of(2000,1,1),
            "66",
            "100",
            "101",
            "2");



    @Test
    void getInsulindose() {
        assertEquals("2",c.getInsulindose());
    }
    @Test
    void setInsulindose() {
        c.setInsulindose("1");
        assertEquals("1",c.getInsulindose());
    }

    @Test
    void getPatientuid() {
        assertEquals(uid,c.getPatientuid());
    }
    @Test
    void setPatientuid() {
        c.setPatientuid(uid2);
        assertEquals(uid2,c.getPatientuid());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2000,1,1),c.getDate());
    }
    @Test
    void setDate() {
        c.setDate(LocalDate.of(2022,1,1));
        assertEquals(LocalDate.of(2022,1,1),c.getDate());
    }


    @Test
    void setTime() {
        c.setTime(12);
        assertEquals(12,c.getTime());
    }

    @Test
    void getBloodglucose() {
        assertEquals("100",c.getBloodglucose());
    }
    @Test
    void setBloodglucose() {
        c.setBloodglucose("123");
        assertEquals("123",c.getBloodglucose());
    }

    @Test
    void getCarbintake() {
        assertEquals("101",c.getCarbintake());
    }
    @Test
    void setCarbintake() {
        c.setCarbintake("124");
        assertEquals("124",c.getCarbintake());
    }

}