package com.glucoclock.database.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

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

	@Column(name = "PostCode")
	private String postCode;


//
//	@Column(name = "Phone")
//	private String Phone;
//
//	@Column(name = "Gender")
//	private String Gender;
//
//	@Column(name = "Diabetes")
//	private String Diabetes;
//
//	@Column(name = "Birth")
//	private LocalDate Birth;
//


	public Patient(String firstName, String lastName, String email, String postCode) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.postCode = postCode;
	}

	public Patient() {

	}

	@Override
	public String toString() {
		return String.format("Patient[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
