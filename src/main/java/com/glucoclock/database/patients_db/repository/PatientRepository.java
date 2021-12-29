package com.glucoclock.database.patients_db.repository;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


// add update delete search
public interface PatientRepository extends CrudRepository<Patient, Long>{
	List<Patient> findByFirstname(String FirstName);
	List<Patient> findAll();
	Patient getPatientById(Long id);
	Patient getPatientByEmail(String email);
	//Find by birthday
	List<Patient> findByBirthdayBetween(LocalDate miniage, LocalDate maxage);

	//Insulin type- rapid
	List<Patient> findByBirthdayBetweenAndGenderAndDiabetestypeAndRapidinsulin(LocalDate miniage, LocalDate maxage, String gender, String diabetestype, boolean rapidindulin);
	//Insulin type- short
	List<Patient> findByBirthdayBetweenAndGenderAndDiabetestypeAndShortinsulin(LocalDate miniage, LocalDate maxage, String gender, String diabetestype, boolean shortinsulin);
	//Insulin type- inter
	List<Patient> findByBirthdayBetweenAndGenderAndDiabetestypeAndIntermediateinsulin(LocalDate miniage, LocalDate maxage, String gender, String diabetestype, boolean intermediateinsulin);
	//Insulin type- long
	List<Patient> findByBirthdayBetweenAndGenderAndDiabetestypeAndLonginsulin(LocalDate miniage, LocalDate maxage, String gender,String diabetestype, boolean longinsulin);
	List<Patient> findByBirthdayBetweenAndDiabetestypeAndRapidinsulinAndShortinsulinAndIntermediateinsulinAndLonginsulin(LocalDate miniage, LocalDate maxage, String diabetestype, boolean rapidindulin, boolean shortinsulin, boolean intermediateinsulin, boolean longinsulin);
	List<Patient> findByBirthdayBetweenAndGenderAndRapidinsulinAndShortinsulinAndIntermediateinsulinAndLonginsulin(LocalDate miniage, LocalDate maxage, String gender, boolean rapidindulin, boolean shortinsulin, boolean intermediateinsulin, boolean longinsulin);
	@Override
	<S extends Patient> S save(S s);

}