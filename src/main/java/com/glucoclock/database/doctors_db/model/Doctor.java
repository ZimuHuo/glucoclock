package com.glucoclock.database.doctors_db.model;

import com.glucoclock.database.Person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Doctor_db")
public class Doctor extends Person {


//    Constructor of a doctor object
    public Doctor(
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
    }

    public Doctor(){

    }


    @Override
    public String toString() {
        return String.format("Doctor[id=%d, firstName='%s', lastName='%s']", id, firstname, lastname);
    }


}
