package com.glucoclock.database.service;

import com.glucoclock.database.model.Patient;
import com.glucoclock.database.model.PatientUI;
import com.glucoclock.database.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import com.glucoclock.database.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.glucoclock.database.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientService  {
    @Autowired
    PatientRepository repository;

    public String bulkcreate(){
        // save a single Patient
        repository.save(new Patient("Zimu", "Huo"));

        // save a list of Patients
        repository.saveAll(Arrays.asList(new Patient("Gao", "zhao")
                , new Patient("Z1", "H1")
                , new Patient("Z2", "H2")
                , new Patient("z3", "h3")));

        return "Patients are created";
    }

    public String create(PatientUI Patient){
        // save a single Patient
        repository.save(new Patient(Patient.getFirstName(), Patient.getLastName()));

        return "Patient is created";
    }

    public List<PatientUI> findAll(){

        List<Patient> Patients = repository.findAll();
        List<PatientUI> PatientUI = new ArrayList<>();

        for (Patient Patient : Patients) {
            PatientUI.add(new PatientUI(Patient.getFirstName(),Patient.getLastName()));
        }

        return PatientUI;
    }


    public String search(long id){
        String Patient = "";
        Patient = repository.findById(id).toString();
        return Patient;
    }


    public List<PatientUI> fetchDataByLastName(String firstname){

        List<Patient> Patients = repository.findByFirstName(firstname);
        List<PatientUI> PatientUI = new ArrayList<>();

        for (Patient Patient : Patients) {
            PatientUI.add(new PatientUI(Patient.getFirstName(),Patient.getLastName()));
        }

        return PatientUI;
    }
}