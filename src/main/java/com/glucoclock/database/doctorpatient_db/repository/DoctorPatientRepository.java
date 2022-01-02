package com.glucoclock.database.doctorpatient_db.repository;

import com.glucoclock.database.doctorpatient_db.model.DoctorPatient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorPatientRepository extends CrudRepository<DoctorPatient,Long> {
    //find doctor of a patient
    DoctorPatient getDoctorPatientByPatientuid(UUID patientuid);
    //find patients of one doctor
    List<DoctorPatient> findByDoctoruid(UUID doctoruid);

    DoctorPatient getDoctorPatientById(long id);


    @Override
    <S extends DoctorPatient> S save(S s);
}
