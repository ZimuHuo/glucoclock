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
    public void updateDoctorLastName(UUID uid, String LName) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setLastName(LName);
        repository.save(doctor);
    }

//    Change first name
    public void updateDoctorFirstName(UUID uid, String FName){
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setFirstName(FName);
        repository.save(doctor);
    }

//    Change email
    public void updateDoctorEmail(UUID uid, String email){
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setEmail(email);
        repository.save(doctor);
    }

//    change postcode
    public void updateDoctorPostCode(UUID uid, String postCode){
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setPostCode(postCode);
        repository.save(doctor);
    }

//    change Address line 1
    public void updateDoctorAddressL1(UUID uid, String newAddressL1) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setHomeAddressL1(newAddressL1);
        repository.save(doctor);
    }

//    change address line 2
    public void updateDoctorAddressL2(UUID uid, String newAddressL2) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setHomeAddressL2(newAddressL2);
        repository.save(doctor);
    }

//    Change city
    public void updateDoctorCity(UUID uid, String city) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setCity(city);
        repository.save(doctor);
    }

//    Change phone number
    public void updateDoctorPhone(UUID uid, String newPhoneNum) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setPhone(newPhoneNum);
        repository.save(doctor);
    }

//    Change gender
    public void updateDoctorGender(UUID uid, String newGender) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setGender(newGender);
        repository.save(doctor);
    }

//    Change birthday
    public void updateDoctorBirthday(UUID uid, LocalDate newBirthday) {
        Doctor doctor = repository.getDoctorByUid(uid);
        doctor.setBirthday(newBirthday);
        repository.save(doctor);
    }

}