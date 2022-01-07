package com.glucoclock.database.doctorpatient_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoctorPatientTest {
    UUID Doctoruid = UUID.randomUUID();
    UUID Patientuid = UUID.randomUUID();
    UUID Doctoruid2 = UUID.randomUUID();
    UUID Patientuid2 = UUID.randomUUID();
    private DoctorPatient dp = new DoctorPatient(Patientuid,Doctoruid);



    @Test
    void getPatientuid() {
        assertEquals(Patientuid,dp.getPatientuid());
    }
    @Test
    void setPatientuid() {
        dp.setPatientuid(Patientuid2);
        assertEquals(Patientuid2,dp.getPatientuid());
    }

    @Test
    void getDoctoruid() {
        assertEquals(Doctoruid,dp.getDoctoruid());
    }
    @Test
    void setDoctoruid() {
        dp.setDoctoruid(Doctoruid2);
        assertEquals(Doctoruid2,dp.getDoctoruid());
    }
}