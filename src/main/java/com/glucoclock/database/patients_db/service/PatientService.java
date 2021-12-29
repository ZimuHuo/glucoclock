package com.glucoclock.database.patients_db.service;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService  {
    @Autowired
    PatientRepository repository;

    public String bulkcreate(){
        // save a single Patient
        repository.save(new Patient(1l,"ZImu","Huo","zimuhuo@outlook.com","flat1","Kings Gate","SW& $AX","New Deli","12345","Male", LocalDate.of(2001,1,1),"Type I",true,true,false,false,"Syringe"));
        repository.save(new Patient(2l,"ZImu2","Huo2","zimuhuo@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Male", LocalDate.of(2000,1,2),"Type II",true,false,true,false,"Injection pen"));
        repository.save(new Patient(3l,"Ann","B","zimuhuo@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Female", LocalDate.of(1985,3,10),"Type II",false,true,false,false,"Injection pen"));
        repository.save(new Patient(4l,"Cindy","B","zimuhuo@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Female", LocalDate.of(1995,10,24),"Type II",false,true,false,false,"Injection pen"));

        return "Patient created";
    }



    public String search(long id){
//        Search for a patient
        String Patient = "";
        Patient = repository.findById(id).toString();
        return Patient;
    }


    public PatientRepository getRepository(){
        return repository;
    }


    public List<Patient> researcherSearch(LocalDate miniage, LocalDate maxage, String gender, String diabetestype,String insulintype){
        List<Patient> patientList = null;
        patientList=repository.findByBirthdayBetween(miniage, maxage);
        Collections.sort(patientList);
        if(insulintype.equals("Rapid acting insulin")) patientList.stream().filter(patient->patient.isRapidInsulin()==true).collect(Collectors.toList());
        if(insulintype.equals("Short acting insulin")) patientList.stream().filter(patient->patient.isShortInsulin()==true).collect(Collectors.toList());
        if(insulintype.equals("Intermediate acting insulin"))patientList.stream().filter(patient->patient.isIntermediateInsulin()==true).collect(Collectors.toList());
        if(insulintype.equals("Long acting insulin")) patientList.stream().filter(patient->patient.isLongInsulin()==true).collect(Collectors.toList());

        System.out.println(patientList);


//        Boolean yes=true;
//        if(insulintype.equals("Rapid acting insulin")) patientList=repository.findByBirthdayBetweenAndGenderAndDiabetestypeAndRapidinsulin(miniage, maxage, gender, diabetestype,yes);
//        if(insulintype.equals("Short acting insulin")) patientList=repository.findByBirthdayBetweenAndGenderAndDiabetestypeAndShortinsulin(miniage, maxage, gender, diabetestype,yes);
//        if(insulintype.equals("Intermediate acting insulin")) patientList=repository.findByBirthdayBetweenAndGenderAndDiabetestypeAndIntermediateinsulin(miniage, maxage, gender, diabetestype,yes);
//        if(insulintype.equals("Long acting insulin")) patientList=repository.findByBirthdayBetweenAndGenderAndDiabetestypeAndLonginsulin(miniage, maxage, gender, diabetestype,yes);
//        //sort the patientlist in age order
//        Collections.sort(patientList);
//        if(patientList==null)System.out.println("patientList is null");
//        else System.out.println("patientList not null");
//        System.out.println(patientList);
//        patientList.stream().filter(patient->patient.getGender()=="Male")
//                .collect(Collectors.toList());
        return patientList;
    }

//    Methods to change patient info in database
//    Change the last name
    public void updatePatientLastName(long id, String LName) {

        Patient patient = repository.getPatientById(id);
        patient.setLastName(LName);
        repository.save(patient);
   }

//   Change the first name
   public void updatePatientFirstName(long id, String FName){
        Patient patient = repository.getPatientById(id);
        patient.setFirstName(FName);
        repository.save(patient);
   }

//   Change the email
    public void updatePatientEmail(long id, String email){
        Patient patient = repository.getPatientById(id);
        patient.setEmail(email);
        repository.save(patient);
    }


//    Change the postcode
    public void updatePatientPostCode(long id, String postCode){
        Patient patient = repository.getPatientById(id);
        patient.setPostCode(postCode);
        repository.save(patient);
    }


//    Change address line 1
    public void updatePatientAddressL1(long id, String newHomeAddressL1) {
        Patient patient = repository.getPatientById(id);
        patient.setHomeAddressL1(newHomeAddressL1);
        repository.save(patient);
    }

//    Change address line 2
    public void updatePatientAddressL2(long id, String newHomeAddressL2) {
        Patient patient = repository.getPatientById(id);
        patient.setHomeAddressL2(newHomeAddressL2);
        repository.save(patient);
    }

//    Change the city
    public void updatePatientCity(long id, String newCity) {
        Patient patient = repository.getPatientById(id);
        patient.setCity(newCity);
        repository.save(patient);
    }

//    Change the phone number
    public void updatePatientPhone(long id, String newPhoneNum) {
        Patient patient = repository.getPatientById(id);
        patient.setPhone(newPhoneNum);
        repository.save(patient);
    }

//    Change the gender
    public void updatePatientGender(long id, String newGender) {
        Patient patient = repository.getPatientById(id);
        patient.setGender(newGender);
        repository.save(patient);
    }

//    Change the birthday
    public void updatePatientBirthday(long id, LocalDate newBirthday) {
        Patient patient = repository.getPatientById(id);
        patient.setBirthday(newBirthday);
        repository.save(patient);
    }

//    Change the type of diabetes
    public void updateDiabetesType(long id, String newDiabetesType) {
        Patient patient = repository.getPatientById(id);
        patient.setDiabetesType(newDiabetesType);
        repository.save(patient);
    }

//    Change the type of insulin
    public void updateInsulinType(long id, boolean rapidInsulin, boolean shortInsulin, boolean intermediateInsulin, boolean longInsulin) {
        Patient patient = repository.getPatientById(id);
        patient.setRapidInsulin(rapidInsulin);
        patient.setShortInsulin(shortInsulin);
        patient.setIntermediateInsulin(intermediateInsulin);
        patient.setLongInsulin(longInsulin);
        repository.save(patient);
    }

//    Change the injection method of insulin
    public void updateInjectionMethod(long id, String injectionMethod) {
        Patient patient = repository.getPatientById(id);
        patient.setInjectionMethod(injectionMethod);
        repository.save(patient);
    }

}