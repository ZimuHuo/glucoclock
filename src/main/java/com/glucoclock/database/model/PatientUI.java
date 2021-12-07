package com.glucoclock.database.model;

public class PatientUI  {
 
		
	private String firstName;
     private String lastName;
     protected PatientUI() {
	}
 
	public PatientUI(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	public String toString() {
		return String.format("Patient[firstName='%s', lastName='%s']", firstName, lastName);
	}
}