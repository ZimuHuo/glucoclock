package com.glucoclock.database.doctors_db.service;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.doctors_db.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class DoctorService  {
    @Autowired
    DoctorRepository repository;

    public String bulkcreate(){
        // save a single Doctor
        repository.save(new Doctor("Yifei","Jin","jinjennifer@sohu.com","warwick","flat46","W8","London","34234432","Female",LocalDate.of(2002,3,8),UUID.fromString("58864138-61ab-49c5-97ef-c98f8c981b0e")));
        repository.save(new Doctor("Ming","Li","liming@sohu.com","flat1","room1","SW& $AX","London","12345","Male", LocalDate.of(2001,1,1), UUID.fromString("111d2815-54fb-4396-94fb-9a071393c336")));
        repository.save(new Doctor("Hong","Xiao","xiaohong@sohu.com","flat2","room3","SW& $AX2","London","12345","Male", LocalDate.of(2001,1,1),UUID.fromString("222d2815-54fb-4396-94fb-9a071393c336")));
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

    //    Methods to change doctor info in database
    //    Change the last name
    public void updateDoctorLastName(long id, String LName) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setLastName(LName);
        repository.save(doctor);
    }

//    Change first name
    public void updateDoctorFirstName(long id, String FName){
        Doctor doctor = repository.getDoctorById(id);
        doctor.setFirstName(FName);
        repository.save(doctor);
    }

//    Change email
    public void updateDoctorEmail(long id, String email){
        Doctor doctor = repository.getDoctorById(id);
        doctor.setEmail(email);
        repository.save(doctor);
    }

//    change postcode
    public void updateDoctorPostCode(long id, String postCode){
        Doctor doctor = repository.getDoctorById(id);
        doctor.setPostCode(postCode);
        repository.save(doctor);
    }

//    change Address line 1
    public void updateDoctorAddressL1(long id, String newAddressL1) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setHomeAddressL1(newAddressL1);
        repository.save(doctor);
    }

//    change address line 2
    public void updateDoctorAddressL2(long id, String newAddressL2) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setHomeAddressL2(newAddressL2);
        repository.save(doctor);
    }

//    Change city
    public void updateDoctorCity(long id, String city) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setCity(city);
        repository.save(doctor);
    }

//    Change phone number
    public void updateDoctorPhone(long id, String newPhoneNum) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setPhone(newPhoneNum);
        repository.save(doctor);
    }

//    Change gender
    public void updateDoctorGender(long id, String newGender) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setGender(newGender);
        repository.save(doctor);
    }

//    Change birthday
    public void updateDoctorBirthday(long id, LocalDate newBirthday) {
        Doctor doctor = repository.getDoctorById(id);
        doctor.setBirthday(newBirthday);
        repository.save(doctor);
    }

}