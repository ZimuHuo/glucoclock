package com.glucoclock.database.researchers_db.model;

import com.glucoclock.database.Person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "researcher_db")
public class Researcher extends Person {

//    Institution
    @Column(name = "Institution")
    private String Institution;


    public Researcher(
            String firstName,
            String lastName,
            String email,
            String homeAddressL1,
            String homeAddressL2,
            String postCode,
            String city,
            String phone,
            String gender,
            LocalDate birthday,
            String institution,
            UUID uid) {

        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.HomeAddressL1 = homeAddressL1;
        this.HomeAddressL2 = homeAddressL2;
        this.postCode = postCode;
        this.City = city;
        this.Phone = phone;
        this.gender = gender;
        this.birthday =  birthday;
        this.uid = uid;
        this.Institution = institution;
    }

    public Researcher() {

    }


    @Override
    public String toString() {
        return String.format("Doctor[id=%d, firstName='%s', lastName='%s']", id, firstname, lastname);
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }
}