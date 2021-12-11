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

@Service
public class ResearcherService  {
    @Autowired
    ResearcherRepository repository;

    public String bulkcreate(){
        // save a single Researcher
        repository.save(new Researcher("ZImu","Huo","zimuhuo@outlook.com","flat1","SW& $AX","12345","Male", LocalDate.of(2001,1,1),"Imperial College London"));
        repository.save(new Researcher("ZImu2","Huo2","zimuhuo@outlook.com","flat2","SW& $AX2","12345","Male", LocalDate.of(2001,1,1),"Imperial College London"));
        return "Researcher created";
    }


    public String search(long id){
        String Researcher = "";
        Researcher = repository.findById(id).toString();
        return Researcher;
    }



    public ResearcherRepository getRepository(){
        return repository;
    }

    public void updateResearcherLastName(long id, String LName) {
        Researcher researcher = repository.getResearcherById(id);
        researcher.setLastName(LName);
        repository.save(researcher);
    }

    public void updateResearcherFirstName(long id, String FName){
        Researcher researcher = repository.getResearcherById(id);
        researcher.setFirstName(FName);
        repository.save(researcher);
    }
    public void updateResearcherEmail(long id, String email){
        Researcher researcher = repository.getResearcherById(id);
        researcher.setEmail(email);
        repository.save(researcher);
    }

    public void updateResearcherPostCode(long id, String postCode){
        Researcher researcher = repository.getResearcherById(id);
        researcher.setPostCode(postCode);
        repository.save(researcher);
    }

    public void updateResearcherAddress(long id, String newHomeAddress) {
        Researcher researcher = repository.getResearcherById(id);
        researcher.setHomeAddress(newHomeAddress);
        repository.save(researcher);
    }

    public void updateResearcherPhone(long id, String newPhoneNum) {
        Researcher researcher = repository.getResearcherById(id);
        researcher.setPhone(newPhoneNum);
        repository.save(researcher);
    }

    public void updateResearcherGender(long id, String newGender) {
        Researcher researcher = repository.getResearcherById(id);
        researcher.setGender(newGender);
        repository.save(researcher);
    }

    public void updateResearcherBirthday(long id, LocalDate newBirthday) {
        Researcher researcher = repository.getResearcherById(id);
        researcher.setBirthday(newBirthday);
        repository.save(researcher);
    }

    public void updateResearcherInstitution(long id, String newInstitution) {
        Researcher researcher = repository.getResearcherById(id);
        researcher.setInstitution(newInstitution);
        repository.save(researcher);
    }

}