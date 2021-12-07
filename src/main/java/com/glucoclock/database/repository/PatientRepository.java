package com.glucoclock.database.repository;

import com.glucoclock.database.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long>{
	List<Patient> findByFirstName(String FirstName);
	List<Patient> findAll();
	Patient getPatientById(Long id);
	@Override
	<S extends Patient> S save(S s);
}