package com.glucoclock.database.researchers_db.service;

import com.glucoclock.database.researchers_db.model.Researcher;
import com.glucoclock.database.researchers_db.model.Researcher;
import com.glucoclock.database.researchers_db.repository.ResearcherRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ResearcherService  {
    @Autowired
    ResearcherRepository repository;



    public String search(long id){
        String Researcher = "";
        Researcher = repository.findById(id).toString();
        return Researcher;
    }



    public ResearcherRepository getRepository(){
        return repository;
    }

    public void updateResearcherLastName(UUID uid, String LName) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setLastName(LName);
        repository.save(researcher);
    }

    public void updateResearcherFirstName(UUID uid, String FName){
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setFirstName(FName);
        repository.save(researcher);
    }
    public void updateResearcherEmail(UUID uid, String email){
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setEmail(email);
        repository.save(researcher);
    }

    public void updateResearcherPostCode(UUID uid, String postCode){
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setPostCode(postCode);
        repository.save(researcher);
    }

    public void updateResearcherAddressL1(UUID uid, String newHomeAddressL1) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setHomeAddressL1(newHomeAddressL1);
        repository.save(researcher);
    }

    public void updateResearcherAddressL2(UUID uid, String newHomeAddressL2) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setHomeAddressL2(newHomeAddressL2);
        repository.save(researcher);
    }

    public void updateResearcherCity(UUID uid, String city) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setCity(city);
        repository.save(researcher);
    }

    public void updateResearcherPhone(UUID uid, String newPhoneNum) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setPhone(newPhoneNum);
        repository.save(researcher);
    }

    public void updateResearcherGender(UUID uid, String newGender) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setGender(newGender);
        repository.save(researcher);
    }

    public void updateResearcherBirthday(UUID uid, LocalDate newBirthday) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setBirthday(newBirthday);
        repository.save(researcher);
    }

    public void updateResearcherInstitution(UUID uid, String newInstitution) {
        Researcher researcher = repository.getResearcherByUid(uid);
        researcher.setInstitution(newInstitution);
        repository.save(researcher);
    }

}