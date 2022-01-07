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

    private Patient p;
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void createPatient(){
        p= new Patient(uid,"Jiaxin","Huang","hjx@outlook.com",
                "flat0","road0","W1J 8FX","London","123456","Female",
                LocalDate.of(2000,1,1),"II",
                false,false,false,false,"xxx");

    }

    @Test
    void testGetFirstName() {
        assertEquals("Jiaxin",p.getFirstName());
    }

    @Test
    void testSetFirstName(){
        p.setFirstName("Good");
        assertEquals("Good",p.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Huang",p.getLastName());
    }
    @Test
    void testSetLastName() {
        p.setLastName("Huo");
        assertEquals("Huo",p.getLastName());
    }
    @Test
    void testGetEmail() {
        assertEquals("hjx@outlook.com",p.getEmail());
    }
    @Test
    void testSetEmail() {
        p.setEmail("zimuhuo@outlook.com");
        assertEquals("zimuhuo@outlook.com", p.getEmail());
    }
    @Test
    void getPostCode() {
        assertEquals("W1J 8FX",p.getPostCode());
    }
    @Test
    void setPostCode() {
        p.setPostCode("SW& $AX");
        assertEquals("SW& $AX",p.getPostCode());
    }

    @Test
    void getHomeAddressL1() {
        assertEquals("flat0",p.getHomeAddressL1());
    }
    @Test
    void setHomeAddressL1() {
        p.setHomeAddressL1("Flat1");
        assertEquals("Flat1",p.getHomeAddressL1());
    }
    @Test
    void getHomeAddressL2() {
        assertEquals("road0",p.getHomeAddressL2());
    }
    @Test
    void setHomeAddressL2() {
        p.setHomeAddressL2("Road1");
        assertEquals("Road1",p.getHomeAddressL2());
    }

    @Test
    void getCity() {
        assertEquals("London",p.getCity());
    }
    @Test
    void setCity() {
        p.setCity("AAA");
        assertEquals("AAA",p.getCity());
    }


    @Test
    void getPhone() {
        assertEquals("123456",p.getPhone());
    }
    @Test
    void setPhone() {
        p.setPhone("12345");
        assertEquals("12345",p.getPhone());
    }

    @Test
    void getGender() {
        p.setGender("Male");
        assertEquals("Male",p.getGender());
    }
    @Test
    void setGender() {
        assertEquals("Female",p.getGender());
    }
    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(2000,1,1),p.getBirthday());
    }
    @Test
    void setBirthday() {
        p.setBirthday(LocalDate.of(2001,1,1));
        assertEquals(LocalDate.of(2001,1,1),p.getBirthday());
    }
    @Test
    void getDiabetesType() {
        assertEquals("II",p.getDiabetesType());
    }
    @Test
    void setDiabetesType() {
        p.setDiabetesType("I");
        assertEquals("I",p.getDiabetesType());
    }

    @Test
    void getInjectionMethod() {
        assertEquals("xxx",p.getInjectionMethod());
    }
    @Test
    void setInjectionMethod() {
        p.setInjectionMethod("xx");
        assertEquals("xx",p.getInjectionMethod());
    }

    @Test
    void getUID() {
        assertEquals(uid,p.getUid());
    }


}