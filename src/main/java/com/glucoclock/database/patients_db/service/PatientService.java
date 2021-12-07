package com.glucoclock.database.patients_db.service;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.model.PatientUI;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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

   public void updatePatientFirstName(long id, String FName){
        Patient patient = repository.getPatientById(id);
        patient.setFirstName(FName);
        repository.save(patient);
   }
    public void updatePatientEmail(long id, String email){
        Patient patient = repository.getPatientById(id);
        patient.setEmail(email);
        repository.save(patient);
    }

    public void updatePatientPostCode(long id, String postCode){
        Patient patient = repository.getPatientById(id);
        patient.setPostCode(postCode);
        repository.save(patient);
    }


}