package com.glucoclock.database.doctorpatient_db.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoctorPatientTest {
    private DoctorPatient dp = new DoctorPatient();
    UUID Doctoruid = UUID.randomUUID();
    UUID Patientuid = UUID.randomUUID();

    @BeforeEach
    void createDP () {
        dp.setDoctoruid(Doctoruid);
        dp.setPatientuid(Patientuid);
    }
    @Test
    void getPatientuid() {
        assertEquals(Patientuid,dp.getPatientuid());
    }

    @Test
    void getDoctoruid() {
        assertEquals(Doctoruid,dp.getDoctoruid());
    }
}