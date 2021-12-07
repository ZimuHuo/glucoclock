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
        repository.save(new Patient("ZImu","Huo","ZImuhuo@outlook.com","SW& $AX"));
        repository.save(new Patient("ZImu2","Huo2","ZImuhuo@outlook.com2","SW& $AX2"));
        return "Patient created";
    }

    public String create(PatientUI Patient){
        // save a single Patient
        repository.save(new Patient(Patient.getFirstName(), Patient.getLastName(),Patient.getEmail(),Patient.getPostCode()));

        return "Patient is created";
    }

    public List<PatientUI> findAll(){

        List<Patient> Patients = repository.findAll();
        List<PatientUI> PatientUI = new ArrayList<>();

        for (Patient Patient : Patients) {
            PatientUI.add(new PatientUI(Patient.getFirstName(),Patient.getLastName(),Patient.getEmail(),Patient.getPostCode()));
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
            PatientUI.add(new PatientUI(Patient.getFirstName(),Patient.getLastName(),Patient.getEmail(),Patient.getPostCode()));
        }
        return PatientUI;
    }
    public PatientRepository getRepository(){
        return repository;
    }
   public void updatePatientLastName(long id, String LName) {
    Patient patient = repository.getPatientById(id);
    patient.setLastName(LName);
    repository.save(patient);
}



}