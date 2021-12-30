package com.glucoclock.database.patients_db.repository;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


// add update delete search
public interface PatientRepository extends CrudRepository<Patient, Long>{
	List<Patient> findByFirstname(String FirstName);
	List<Patient> findAll();
	Patient getPatientById(Long id);
	Patient getPatientByEmail(String email);
	Patient getPatientByUid(UUID uid);
	//Find by birthday
	List<Patient> findByBirthdayBetween(LocalDate miniage, LocalDate maxage);
@Override
	<S extends Patient> S save(S s);

}