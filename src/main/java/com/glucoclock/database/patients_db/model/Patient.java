package com.glucoclock.database.patients_db.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Patient_db")
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
	@Column(name = "firstname")
	private String firstName;
 
	@Column(name = "lastname")
	private String lastName;

	@Column(name = "Email")
	private String email;

	@Column(name = "HomeAddress")
	private String HomeAddress;

	@Column(name = "PostCode")
	private String postCode;

	@Column(name = "Phone")
	private String Phone;

	@Column(name = "Gender")
	private String Gender;

	@Column(name = "Birthday")
	private LocalDate Birthday;

	@Column(name = "DiabetesType")
	private String DiabetesType;

	@Column(name = "InsulinType")
	private String InsulinType;



	public Patient(String firstName, String lastName, String email, String homeAddress, String postCode, String phone, String gender, LocalDate birthday, String insulinType) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.HomeAddress = homeAddress;
		this.postCode = postCode;
		this.Phone = phone;
		this.Gender = gender;
		this.Birthday =  birthday;
		this.InsulinType = insulinType;
	}

	public Patient() {

	}

	@Override
	public String toString() {
		return String.format("Patient[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

//	getters and setters
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

	public String getHomeAddress() {
		return HomeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		HomeAddress = homeAddress;
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

	public String getInsulinType() {
		return InsulinType;
	}

	public void setInsulinType(String insulinType) {
		InsulinType = insulinType;
	}

	public String getDiabetesType() {
		return DiabetesType;
	}

	public void setDiabetesType(String diabetesType) {
		DiabetesType = diabetesType;
	}
}
