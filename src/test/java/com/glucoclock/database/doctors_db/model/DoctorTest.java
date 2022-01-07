package com.glucoclock.database.doctors_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    private Doctor d = new Doctor();
    UUID uid = UUID.randomUUID();

    @BeforeEach
    void creatDoctor(){
        d.setUID(uid);
        d.setLastName("Huo");
        d.setFirstName("ZImu");
        d.setEmail("zimuhuo@outlook.com");
        d.setBirthday(LocalDate.of(2001,1,1));
        d.setCity("London");
        d.setGender("Male");
        d.setHomeAddressL1("Flat1");
        d.setHomeAddressL2("Road1");
        d.setPhone("12345");
        d.setPostCode("SW& $AX");
    }
    @Test
    void GetFirstName() {
        assertEquals("ZImu",d.getFirstName());
    }

    @Test
    void GetLastName() {
        assertEquals("Huo",d.getLastName());
    }

    @Test
    void GetEmail() {
        assertEquals("zimuhuo@outlook.com",d.getEmail());
    }

    @Test
    void getPostCode() {
        assertEquals("SW& $AX",d.getPostCode());
    }

    @Test
    void getHomeAddressL1() {
        assertEquals("Flat1",d.getHomeAddressL1());
    }

    @Test
    void getHomeAddressL2() {
        assertEquals("Road1",d.getHomeAddressL2());
    }

    @Test
    void getCity() {
        assertEquals("London",d.getCity());
    }

    @Test
    void getPhone() {
        assertEquals("12345",d.getPhone());
    }

    @Test
    void getGender() {
        assertEquals("Male",d.getGender());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(2001,1,1),d.getBirthday());
    }

    @Test
    void getUID() {
        assertEquals(uid,d.getUID());
    }
}