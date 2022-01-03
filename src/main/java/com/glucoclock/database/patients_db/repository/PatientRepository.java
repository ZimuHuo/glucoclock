package com.glucoclock.database.patients_db.repository;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


// add update delete search
public interface PatientRepository extends CrudRepository<Patient, Long>{


//Find Single Patient
	// by email
	Patient getPatientByEmail(String email);
	// by uuid
	Patient getPatientByUid(UUID uid);
	// by id
	Patient getPatientById(Long id);
//Find List
	// by birthday
	List<Patient> findByBirthdayBetween(LocalDate miniage, LocalDate maxage);
	// by firstname
	List<Patient> findByFirstname(String FirstName);
	List<Patient> findAll();
@Override
	<S extends Patient> S save(S s);

}