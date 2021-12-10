package com.glucoclock.database.doctors_db.repository;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// add update delete search
public interface DoctorRepository extends CrudRepository<Doctor, Long>{
    List<Doctor> findByFirstName(String FirstName);
    List<Doctor> findAll();
    Doctor getDoctorById(Long id);
    Doctor getDoctorByEmail(String email);
    @Override
    <S extends Doctor> S save(S s);


}