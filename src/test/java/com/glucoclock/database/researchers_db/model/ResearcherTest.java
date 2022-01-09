package com.glucoclock.database.researchers_db.model;

import com.glucoclock.database.doctors_db.model.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ResearcherTest {

    UUID uid = UUID.randomUUID();
    UUID uid2 = UUID.randomUUID();
    private Researcher d = new Researcher("Jiaxin","Huang","hjx@gmail.com",
            "flat","road","W12 8HU",
            "SZ","123","female",
            LocalDate.of(2000,1,1),
            "ICL",
            uid);

    @Test
    void GetFirstName() {
        assertEquals("Jiaxin",d.getFirstName());
    }
    @Test
    void SetFirstName() {
        d.setFirstName("ZImu");assertEquals("ZImu",d.getFirstName());
    }

    @Test
    void GetLastName() {
        assertEquals("Huang",d.getLastName());
    }
    @Test
    void SetLastName() {
        d.setLastName("Huo");
        assertEquals("Huo",d.getLastName());
    }

    @Test
    void GetEmail() {
        assertEquals("hjx@gmail.com",d.getEmail());
    }
    @Test
    void SetEmail() {
        d.setEmail("zimuhuo@outlook.com");assertEquals("zimuhuo@outlook.com",d.getEmail());
    }

    @Test
    void getPostCode() {
        assertEquals("W12 8HU",d.getPostCode());
    }
    @Test
    void setPostCode() {
        d.setPostCode("SW& $AX");
        assertEquals("SW& $AX",d.getPostCode());
    }

    @Test
    void getHomeAddressL1() {
        assertEquals("flat",d.getHomeAddressL1());
    }
    @Test
    void setHomeAddressL1() {
        d.setHomeAddressL1("Flat1");
        assertEquals("Flat1",d.getHomeAddressL1());
    }

    @Test
    void getHomeAddressL2() {
        assertEquals("road",d.getHomeAddressL2());
    }
    @Test
    void setHomeAddressL2() {
        d.setHomeAddressL2("Road1");assertEquals("Road1",d.getHomeAddressL2());
    }

    @Test
    void getCity() {
        assertEquals("SZ",d.getCity());
    }
    @Test
    void setCity() {
        d.setCity("London");assertEquals("London",d.getCity());
    }

    @Test
    void getPhone() {
        assertEquals("123",d.getPhone());
    }
    @Test
    void setPhone() {
        d.setPhone("12345");assertEquals("12345",d.getPhone());
    }

    @Test
    void getGender() {
        assertEquals("female",d.getGender());
    }
    @Test
    void setGender() {
        d.setGender("Male");
        assertEquals("Male",d.getGender());
    }

    @Test
    void setBirthday() {
        d.setBirthday(LocalDate.of(2001,1,1));assertEquals(LocalDate.of(2001,1,1),d.getBirthday());
    }
    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(2000,1,1),d.getBirthday());
    }
    @Test
    void getUID() {
        assertEquals(uid,d.getUid());
    }
    @Test
    void setUID() {
        d.setUID(uid2);
        assertEquals(uid2,d.getUID());
    }

    @Test
    void getInstitution() {
        assertEquals("ICL",d.getInstitution());
    }

    @Test
    void setInstitution() {
        d.setInstitution("IC");assertEquals("IC",d.getInstitution());
    }
}