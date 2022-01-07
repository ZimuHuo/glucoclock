package com.glucoclock.database.researchers_db.model;

import com.glucoclock.database.doctors_db.model.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ResearcherTest {
    private Researcher r = new Researcher();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void creatResearcher(){
        r.setUID(uid);
        r.setLastName("Huo");
        r.setFirstName("ZImu");
        r.setEmail("zimuhuo@outlook.com");
        r.setBirthday(LocalDate.of(2001,1,1));
        r.setCity("London");
        r.setGender("Male");
        r.setHomeAddressL1("Flat1");
        r.setHomeAddressL2("Road1");
        r.setPhone("12345");
        r.setPostCode("SW& $AX");
        r.setInstitution("ICL");
    }
    @Test
    void GetFirstName() {
        assertEquals("ZImu",r.getFirstName());
    }

    @Test
    void GetLastName() {
        assertEquals("Huo",r.getLastName());
    }

    @Test
    void GetEmail() {
        assertEquals("zimuhuo@outlook.com",r.getEmail());
    }

    @Test
    void getPostCode() {
        assertEquals("SW& $AX",r.getPostCode());
    }

    @Test
    void getHomeAddressL1() {
        assertEquals("Flat1",r.getHomeAddressL1());
    }

    @Test
    void getHomeAddressL2() {
        assertEquals("Road1",r.getHomeAddressL2());
    }

    @Test
    void getCity() {
        assertEquals("London",r.getCity());
    }

    @Test
    void getPhone() {
        assertEquals("12345",r.getPhone());
    }

    @Test
    void getGender() {
        assertEquals("Male",r.getGender());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(2001,1,1),r.getBirthday());
    }

    @Test
    void getUID() {
        assertEquals(uid,r.getUID());
    }

    @Test
    void getInstitution() {
        assertEquals("ICL",r.getInstitution());
    }
}