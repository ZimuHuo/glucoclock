package com.glucoclock.database.patients_db.service;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private PatientRepository patientRepository;

    private  PatientService patientService;

    UUID uid = UUID.randomUUID();

    Patient p = new Patient(uid,"ZImu","Huo","zimuhuo@outlook.com",
            "flat1","Road","SW& $AX","London","12345","Male",
            LocalDate.of(2001,1,1),"I",
            true,true,true,true,"tyu");


//    @BeforeEach
//    void setupService() {
//        patientRepository = mock(PatientRepository.class);
//        patientService = new PatientService();
//        patientService.getRepository().save(p);
//    }


    @Test
    void updatesPatientLastName() {

     Patient p2 = new Patient();
     p2.setLastName("hhh");
     p2.setUid(uid);
     patientRepository.save(p2);
     patientService.updatePatientLastName(uid,"new");
        //patientService.getRepository().save(p);
        assertEquals(uid,patientRepository.getPatientByUid(uid));
        //System.out.println(patientRepository.getPatientByUid(uid).getLastName());
    }


}