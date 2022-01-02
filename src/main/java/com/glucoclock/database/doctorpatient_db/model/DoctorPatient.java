package com.glucoclock.database.doctorpatient_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Doctor_Patient_db")
public class DoctorPatient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Columns of the database and variables:
    //patient uid
    @Column(name="PatientUid")
    private UUID patientuid;

    //doctor uid
    @Column(name="DoctorUid")
    private  UUID doctoruid;

    @Override
    public String toString() {
        return "DoctorPatient{" +
                "patientuid=" + patientuid +
                ", doctoruid=" + doctoruid +
                '}';
    }

    //  Constructor
    public DoctorPatient(UUID patientuid, UUID doctoruid) {
        this.patientuid = patientuid;
        this.doctoruid = doctoruid;
    }

    public DoctorPatient() {

    }

    //  getter and setter

    public UUID getPatientuid() {
        return patientuid;
    }

    public void setPatientuid(UUID patientuid) {
        this.patientuid = patientuid;
    }

    public UUID getDoctoruid() {
        return doctoruid;
    }

    public void setDoctoruid(UUID doctoruid) {
        this.doctoruid = doctoruid;
    }
}
