package com.glucoclock.database.researchers_db.repository;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.researchers_db.model.Researcher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

// add update delete search
public interface ResearcherRepository extends CrudRepository<Researcher, Long>{
    List<Researcher> findByFirstName(String FirstName);
    List<Researcher> findAll();
    Researcher getResearcherById(Long id);
    Researcher getResearcherByEmail(String email);
    Researcher getResearcherByUid(UUID uid);
    @Override
    <S extends Researcher> S save(S s);


}