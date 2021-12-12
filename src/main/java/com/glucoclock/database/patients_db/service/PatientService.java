package com.glucoclock.database.patients_db.service;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Set;

@Service
public class PatientService  {
    @Autowired
    PatientRepository repository;

    public String bulkcreate(){
        // save a single Patient
        repository.save(new Patient("ZImu","Huo","zimuhuo@outlook.com","flat1","SW& $AX","12345","Male", LocalDate.of(2001,1,1),"Type I","[\"Rapid-acting insulin\",\"Short-acting insulin\",\"Intermediate-acting insulin\"]","[\"Syringe\", \"Injection pen\"]"));
        repository.save(new Patient("ZImu2","Huo2","zimuhuo@outlook.com","flat2","SW& $AX2","12345","Male", LocalDate.of(2001,1,1),"Type II","[\"Rapid-acting insulin\",\"Short-acting insulin\",\"Intermediate-acting insulin\"]","[\"Syringe\", \"Injection pen\"]"));
        return "Patient created";
    }



    public String search(long id){
        String Patient = "";
        Patient = repository.findById(id).toString();
        return Patient;
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

    public void updatePatientAddress(long id, String newHomeAddress) {
        Patient patient = repository.getPatientById(id);
        patient.setHomeAddress(newHomeAddress);
        repository.save(patient);
    }

    public void updatePatientPhone(long id, String newPhoneNum) {
        Patient patient = repository.getPatientById(id);
        patient.setPhone(newPhoneNum);
        repository.save(patient);
    }

    public void updatePatientGender(long id, String newGender) {
        Patient patient = repository.getPatientById(id);
        patient.setGender(newGender);
        repository.save(patient);
    }

    public void updatePatientBirthday(long id, LocalDate newBirthday) {
        Patient patient = repository.getPatientById(id);
        patient.setBirthday(newBirthday);
        repository.save(patient);
    }

    public void updateDiabetesType(long id, String newDiabetesType) {
        Patient patient = repository.getPatientById(id);
        patient.setDiabetesType(newDiabetesType);
        repository.save(patient);
    }

    public void updateInsulinType(long id, Set<String> insulinType) {
        Patient patient = repository.getPatientById(id);
        Gson gson = new Gson();
        patient.setInsulinType(gson.toJson(insulinType));
        repository.save(patient);
    }

    public void updateInjectionMethod(long id, Set<String> injectionMethod) {
        Patient patient = repository.getPatientById(id);
        Gson gson = new Gson();
        patient.setInjectionMethod(gson.toJson(injectionMethod));
        repository.save(patient);
    }

}