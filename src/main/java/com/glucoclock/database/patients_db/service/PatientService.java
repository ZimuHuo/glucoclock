package com.glucoclock.database.patients_db.service;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService  {
    @Autowired
    PatientRepository repository;

    public String bulkcreate(){
        // save a single Patient
        repository.save(new Patient(UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336"),"ZImu","Huo","zimuhuo@outlook.com","flat1","Kings Gate","SW& $AX","New Deli","12345","Male", LocalDate.of(2001,1,1),"Type I",true,true,false,false,"Syringe"));
        repository.save(new Patient(UUID.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),"Gang","Wang","wanggang@sohu.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Male", LocalDate.of(2000,1,2),"Type II",true,false,true,false,"Injection pen"));
        repository.save(new Patient(UUID.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),"Ann","B","Annb@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Female", LocalDate.of(1985,3,10),"Type II",false,true,false,false,"Injection pen"));
        repository.save(new Patient(UUID.fromString("1e522787-854c-41f6-81b1-cd169b6d3c3d"),"Cindy","B","Cindyb@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Female", LocalDate.of(1995,10,24),"Type II",false,true,false,false,"Injection pen"));

        return "Patient created";
    }



    public String search(long id){
//        Search for a patient
        String Patient = "";
        Patient = repository.findById(id).toString();
        return Patient;
    }

    public String searchPatientname(UUID patientid){
        //find the patient name
        String patientname;
        patientname=repository.getPatientByUid(patientid).getFirstName()+" "+repository.getPatientByUid(patientid).getLastName();
        return patientname;
    }

    public UUID searchPatientuid(String patientEmail){
        UUID returnuid=null;
        Patient patient;
        patient=repository.getPatientByEmail(patientEmail);
        //check exist of the patient
        if(patient!=null) returnuid=patient.getUid();
        return returnuid;
    }

    public Patient searchByuid(UUID patientuid){
        return repository.getPatientByUid(patientuid);
    }

    public PatientRepository getRepository(){
        return repository;
    }


    public List<Patient> researcherSearch(LocalDate miniage, LocalDate maxage, String gender, String diabetestype,String insulintype){
        List<Patient> patientList = null;
        patientList=repository.findByBirthdayBetween(miniage, maxage);
        Collections.sort(patientList);


        //filter 2: check the insulin type
        if(!insulintype.equals("Any")){
            if (insulintype.equals("Rapid acting insulin")) {
                patientList = patientList.stream()
                        .filter(patient -> patient.isRapidInsulin())
                        .collect(Collectors.toList());
            } else if (insulintype.equals("Short acting insulin")) {
                patientList = patientList.stream()
                        .filter(patient -> patient.isShortInsulin())
                        .collect(Collectors.toList());
            } else if (insulintype.equals("Intermediate acting insulin")) {
                patientList = patientList.stream()
                        .filter(patient -> patient.isIntermediateInsulin())
                        .collect(Collectors.toList());
            } else{
                patientList = patientList.stream()
                        .filter(patient -> patient.isLongInsulin())
                        .collect(Collectors.toList());
            }
        }

        //filter 3: check the gender, skip if any
        if(!gender.equals("Any")){
            if (gender.equals("Female")) {
                patientList = patientList.stream()
                        .filter(patient -> patient.isFemale())
                        .collect(Collectors.toList());
            } else {
                patientList = patientList.stream()
                        .filter(patient -> patient.isMale())
                        .collect(Collectors.toList());
            }
        }

        //filter 4: diabetes type
        if(!diabetestype.equals("Any")) {
            if (diabetestype.equals("Type I")) {
                patientList = patientList.stream()
                        .filter(patient -> patient.isType1())
                        .collect(Collectors.toList());
            } else {
                patientList = patientList.stream()
                        .filter(patient -> patient.isType2())
                        .collect(Collectors.toList());
            }

        }

        return patientList;
    }

//    Methods to change patient info in database
//    Change the last name
    public void updatePatientLastName(UUID uid, String LName) {

        Patient patient = repository.getPatientByUid(uid);
        patient.setLastName(LName);
        repository.save(patient);
   }

//   Change the first name
   public void updatePatientFirstName(UUID uid, String FName){
        Patient patient = repository.getPatientByUid(uid);
        patient.setFirstName(FName);
        repository.save(patient);
   }

//   Change the email
    public void updatePatientEmail(UUID uid, String email){
        Patient patient = repository.getPatientByUid(uid);
        patient.setEmail(email);
        repository.save(patient);
    }


//    Change the postcode
    public void updatePatientPostCode(UUID uid, String postCode){
        Patient patient = repository.getPatientByUid(uid);
        patient.setPostCode(postCode);
        repository.save(patient);
    }


//    Change address line 1
    public void updatePatientAddressL1(UUID uid, String newHomeAddressL1) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setHomeAddressL1(newHomeAddressL1);
        repository.save(patient);
    }

//    Change address line 2
    public void updatePatientAddressL2(UUID uid, String newHomeAddressL2) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setHomeAddressL2(newHomeAddressL2);
        repository.save(patient);
    }

//    Change the city
    public void updatePatientCity(UUID uid, String newCity) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setCity(newCity);
        repository.save(patient);
    }

//    Change the phone number
    public void updatePatientPhone(UUID uid, String newPhoneNum) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setPhone(newPhoneNum);
        repository.save(patient);
    }

//    Change the gender
    public void updatePatientGender(UUID uid, String newGender) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setGender(newGender);
        repository.save(patient);
    }

//    Change the birthday
    public void updatePatientBirthday(UUID uid, LocalDate newBirthday) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setBirthday(newBirthday);
        repository.save(patient);
    }

//    Change the type of diabetes
    public void updateDiabetesType(UUID uid, String newDiabetesType) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setDiabetesType(newDiabetesType);
        repository.save(patient);
    }

//    Change the type of insulin
    public void updateInsulinType(UUID uid, boolean rapidInsulin, boolean shortInsulin, boolean intermediateInsulin, boolean longInsulin) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setRapidInsulin(rapidInsulin);
        patient.setShortInsulin(shortInsulin);
        patient.setIntermediateInsulin(intermediateInsulin);
        patient.setLongInsulin(longInsulin);
        repository.save(patient);
    }

//    Change the injection method of insulin
    public void updateInjectionMethod(UUID uid, String injectionMethod) {
        Patient patient = repository.getPatientByUid(uid);
        patient.setInjectionMethod(injectionMethod);
        repository.save(patient);
    }

}