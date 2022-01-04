package com.glucoclock.database.patients_db.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private Patient p = new Patient(UUID.randomUUID(),"ZImu","Huo","zimuhuo@outlook.com",
            "flat1","Road","SW& $AX","London","12345","Male",
            LocalDate.of(2001,1,1),"I",
            true,true,true,true,"tyu");

    @Test
    void testGetFirstName() {
        assertEquals("ZImu",p.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Huo",p.getLastName());
    }

    @Test
    void testGetEmail() {
        assertEquals("zimuhuo@outlook.com",p.getEmail());
    }

    @Test
    void getPostCode() {
    }

    @Test
    void getHomeAddressL1() {
    }

    @Test
    void getHomeAddressL2() {
    }

    @Test
    void getCity() {
    }

    @Test
    void getPhone() {
    }

    @Test
    void getGender() {
    }

    @Test
    void getBirthday() {
    }

    @Test
    void getDiabetesType() {
    }

    @Test
    void getInjectionMethod() {
    }
}