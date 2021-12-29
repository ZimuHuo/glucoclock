package com.glucoclock.database.researchers_db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "researcher_db")
public class Researcher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Columns of the database and variables:
//	First Name of patient
    @Column(name = "firstname")
    private String firstName;

    //	last name of patient
    @Column(name = "lastname")
    private String lastName;

    //  Email
    @Column(name = "Email")
    private String email;

    //	Address line1
    @Column(name = "HomeAddressL1")
    private String HomeAddressL1;

    //	Address line2
    @Column(name = "HomeAddressL2")
    private String HomeAddressL2;

    //	Postcode
    @Column(name = "PostCode")
    private String postCode;

    //	City
    @Column(name = "City")
    private String City;

    //	Phone number
    @Column(name = "Phone")
    private String Phone;

    //	Gender
    @Column(name = "Gender")
    private String Gender;

    //	Birthday
    @Column(name = "Birthday")
    private LocalDate Birthday;

//    Institution
    @Column(name = "Institution")
    private String Institution;

    //	UID
    @Column(name = "UID")
    private UUID UID;


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

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.HomeAddressL1 = homeAddressL1;
        this.HomeAddressL2 = homeAddressL2;
        this.postCode = postCode;
        this.City = city;
        this.Phone = phone;
        this.Gender = gender;
        this.Birthday =  birthday;
        this.UID = uid;
        this.Institution = institution;
    }

    public Researcher() {

    }


    @Override
    public String toString() {
        return String.format("Doctor[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }


//    Getters and setters


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public UUID getUID() {
        return UID;
    }

    public void setUID(UUID UID) {
        this.UID = UID;
    }
}