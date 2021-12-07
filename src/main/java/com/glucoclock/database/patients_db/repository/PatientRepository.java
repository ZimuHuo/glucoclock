package com.glucoclock.database.patients_db.repository;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// add update delete search
public interface PatientRepository extends CrudRepository<Patient, Long>{
	List<Patient> findByFirstName(String FirstName);
	List<Patient> findAll();
	Patient getPatientById(Long id);
	Patient getPatientByEmail(String email);
	@Override
	<S extends Patient> S save(S s);

}