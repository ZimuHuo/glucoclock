package com.glucoclock.database.model;

import javax.persistence.*;
import java.io.Serializable;

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
 
	protected Patient() {
	}
 
	public Patient(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
}