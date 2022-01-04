package com.glucoclock.database.patients_db.service;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceTest {
    private PatientRepository patientRepository;
    private  PatientService patientService;

    @BeforeEach
    void setupService() {
        patientRepository = mock(PatientRepository.class);
        patientService = new PatientService();
    }

    private Patient p = new Patient(UUID.randomUUID(),"ZImu","Huo","zimuhuo@outlook.com",
            "flat1","Road","SW& $AX","London","12345","Male",
            LocalDate.of(2001,1,1),"I",
            true,true,true,true,"tyu");

    /*
    @Test
    void updatePatientLastName() {
        when(patientRepository.findById(1L)).thenReturn();
    }
    */

}