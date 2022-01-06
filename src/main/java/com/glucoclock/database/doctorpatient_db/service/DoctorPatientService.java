package com.glucoclock.database.doctorpatient_db.service;

import com.glucoclock.database.doctorpatient_db.model.DoctorPatient;
import com.glucoclock.database.doctorpatient_db.repository.DoctorPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorPatientService {
    @Autowired
    DoctorPatientRepository repository;

    public String bulkcreate(){
        //testing data
        repository.save(new DoctorPatient(UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336"),UUID.fromString("58864138-61ab-49c5-97ef-c98f8c981b0e")));
        return "DoctorPatient created";
    }

    public String create(UUID patientuid, UUID doctoruid){
        //add data to the database
        repository.save(new DoctorPatient(patientuid,doctoruid));
        return "DoctorPatient created";
    }

    public String search(long id){
        String Doctorpatient="";
        Doctorpatient=repository.findById(id).toString();
        return Doctorpatient;
    }

    public DoctorPatientRepository getRepository(){return repository;}

    //search patient for one doctor
    public List<DoctorPatient> getPatientlist(UUID doctoruid){
        List<DoctorPatient> patientList = repository.findByDoctoruid(doctoruid);
        return patientList;
    }
    //search patients for a doctor, return patient id
    public List<UUID> getPatientidlist(UUID doctoruid){
        //get list of DoctorPatient
        List<DoctorPatient> patientList = repository.findByDoctoruid(doctoruid);
        //change to UUID
        List<UUID> patientidList = new ArrayList<>();
        for(DoctorPatient thispatient:patientList){
            UUID patientid = thispatient.getPatientuid();
            patientidList.add(patientid);
        }
        return patientidList;
    }

    //Methods to change doctor-patient info in database
    //change patient uid
    public void updatePatientUid(long id, UUID patientuid){
        DoctorPatient doctorpatient=repository.getDoctorPatientById(id);
        doctorpatient.setPatientuid(patientuid);
    }
    //change doctor uid
    public void updateDoctorUid(long id, UUID doctoruid){
        DoctorPatient doctorpatient=repository.getDoctorPatientById(id);
        doctorpatient.setDoctoruid(doctoruid);
    }
    public boolean exist(UUID patientuid){
        return repository.getDoctorPatientByPatientuid(patientuid)!=null;
    }

    //delete
    public void deletePatient(UUID patientuid){
        DoctorPatient delete = repository.getDoctorPatientByPatientuid(patientuid);
        repository.delete(delete);
    }

}
