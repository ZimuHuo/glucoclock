package com.glucoclock.database.Person;

import com.glucoclock.database.patients_db.model.Patient;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public abstract class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    //Columns of the database and variables:
// 	UID
    @Column(name = "UID")
    protected UUID uid;
    //	First Name of patient
    @Column(name = "Firstname")
    protected String firstname;
    //	last name of patient
    @Column(name = "Lastname")
    protected String lastname;
    @Column(name = "Email")
    protected String email;
    //	Address line1
    @Column(name = "HomeAddressL1")
    protected String HomeAddressL1;
    //	Address line2
    @Column(name = "HomeAddressL2")
    protected String HomeAddressL2;
    //	Postcode
    @Column(name = "PostCode")
    protected String postCode;
    //	City
    @Column(name = "City")
    protected String City;
    //	Phone number
    @Column(name = "Phone")
    protected String Phone;
    //	Gender
    @Column(name = "Gender")
    protected String gender;
    //	Birthday
    @Column(name = "Birthday")
    protected LocalDate birthday;

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHomeAddressL1() {
        return HomeAddressL1;
    }

    public void setHomeAddressL1(String homeAddressL1) {
        HomeAddressL1 = homeAddressL1;
    }

    public String getHomeAddressL2() {
        return HomeAddressL2;
    }

    public void setHomeAddressL2(String homeAddressL2) {
        HomeAddressL2 = homeAddressL2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
