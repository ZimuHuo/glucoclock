package com.glucoclock.database.patients_db.model;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import com.glucoclock.database.patients_db.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient p = new Patient();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void createPatient(){
        p.setUid(uid);
        p.setLastName("Huo");
        p.setFirstName("ZImu");
        p.setEmail("zimuhuo@outlook.com");
        p.setBirthday(LocalDate.of(2001,1,1));
        p.setCity("London");
        p.setDiabetesType("1");
        p.setGender("Male");
        p.setHomeAddressL1("Flat1");
        p.setHomeAddressL2("Road1");
        p.setInjectionMethod("xxx");
        p.setLogbooktype("simple");
        p.setPhone("12345");
        p.setPostCode("SW& $AX");

    }

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
        assertEquals("SW& $AX",p.getPostCode());
    }

    @Test
    void getHomeAddressL1() {
        assertEquals("Flat1",p.getHomeAddressL1());
    }

    @Test
    void getHomeAddressL2() {
        assertEquals("Road1",p.getHomeAddressL2());
    }

    @Test
    void getCity() {
        assertEquals("London",p.getCity());
    }

    @Test
    void getPhone() {
        assertEquals("12345",p.getPhone());
    }

    @Test
    void getGender() {
        assertEquals("Male",p.getGender());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(2001,1,1),p.getBirthday());
    }

    @Test
    void getDiabetesType() {
        assertEquals("1",p.getDiabetesType());
    }

    @Test
    void getInjectionMethod() {
        assertEquals("xxx",p.getInjectionMethod());
    }

    @Test
    void getUID() {
        assertEquals(uid,p.getUid());
    }

}