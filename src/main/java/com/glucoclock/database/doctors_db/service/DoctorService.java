package com.glucoclock.database.doctors_db.service;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.doctors_db.repository.DoctorRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DoctorService  {
    @Autowired
    DoctorRepository repository;

    public String bulkcreate(){
        // save a single Doctor
        repository.save(new Doctor("ZImu","Huo","zimuhuo@outlook.com","flat1","SW& $AX","12345","Male", LocalDate.of(2001,1,1)));
        repository.save(new Doctor("ZImu2","Huo2","zimuhuo@outlook.com","flat2","SW& $AX2","12345","Male", LocalDate.of(2001,1,1)));
        return "Doctor created";
    }


    public String search(long id){
        String Doctor = "";
        Doctor = repository.findById(id).toString();
        return Doctor;
    }

    

    public DoctorRepository getRepository(){
        return repository;
    }

    public void updateDoctorLastName(long id, String LName) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setLastName(LName);
        repository.save(doctor);
    }

    public void updateDoctorFirstName(long id, String FName){
        Doctor doctor = repository.getDoctorById(id);
        doctor.setFirstName(FName);
        repository.save(doctor);
    }
    public void updateDoctorEmail(long id, String email){
        Doctor doctor = repository.getDoctorById(id);
        doctor.setEmail(email);
        repository.save(doctor);
    }

    public void updateDoctorPostCode(long id, String postCode){
        Doctor doctor = repository.getDoctorById(id);
        doctor.setPostCode(postCode);
        repository.save(doctor);
    }

    public void updateDoctorAddress(long id, String newHomeAddress) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setHomeAddress(newHomeAddress);
        repository.save(doctor);
    }

    public void updateDoctorPhone(long id, String newPhoneNum) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setPhone(newPhoneNum);
        repository.save(doctor);
    }

    public void updateDoctorGender(long id, String newGender) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setGender(newGender);
        repository.save(doctor);
    }

    public void updateDoctorBirthday(long id, LocalDate newBirthday) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setBirthday(newBirthday);
        repository.save(doctor);
    }

}