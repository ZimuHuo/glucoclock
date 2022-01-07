package com.glucoclock.database.intensiveLogBook_db.model;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IntensiveLogBookTest {
    UUID uid = UUID.randomUUID();
    private IntensiveLogBook i = new IntensiveLogBook(uid,
            LocalDate.of(2022,1,1),
            LocalTime.of(10,00,00),
            "1","1","1","1","1","1","1"
            );



    @Test
    void getCarbbolus() {
        assertEquals("1",i.getCarbbolus());
    }
    @Test
    void setCarbbolus() {
        i.setCarbbolus("2");
        assertEquals("2",i.getCarbbolus());
    }
    @Test
    void getHighbsbolus() {
        assertEquals("1",i.getHighbsbolus());
    }
    @Test
    void setHighbsbolus() {
        i.setHighbsbolus("200");
        assertEquals("200",i.getHighbsbolus());
    }

    @Test
    void getBasalrate() {
        assertEquals("1",i.getBasalrate());
    }
    @Test
    void setBasalrate() {
        i.setBasalrate("2");
        assertEquals("2",i.getBasalrate());
    }

    @Test
    void getKetons() {
        assertEquals("1",i.getKetons());
    }
    @Test
    void setKetons() {
        i.setKetons("2");
        assertEquals("2",i.getKetons());
    }
    @Test
    void setInsulindose() {
        i.setInsulindose("2");
        assertEquals("2",i.getInsulindose());
    }

    @Test
    void setId() {
        i.setId(1L);
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
    void setDate() {
        i.setDate(LocalDate.of(2001,1,1));
        assertEquals(LocalDate.of(2001,1,1),i.getDate());
    }

    @Test
    void getTime() {
        assertEquals(LocalTime.of(10,00,00),i.getTime());
    }
    @Test
    void setTime() {
        i.setTime(LocalTime.of(11,00,00));
        assertEquals(LocalTime.of(11,00,00),i.getTime());
    }
    @Test
    void getBloodglucose() {
        assertEquals("1",i.getBloodglucose());
    }
    @Test
    void setBloodglucose() {
        i.setBloodglucose("2");
        assertEquals("2",i.getBloodglucose());
    }

    @Test
    void getCarbintake() {
        assertEquals("1",i.getCarbintake());
    }
    @Test
    void setCarbintake() {
        i.setCarbintake("2");
        assertEquals("2",i.getCarbintake());
    }
}