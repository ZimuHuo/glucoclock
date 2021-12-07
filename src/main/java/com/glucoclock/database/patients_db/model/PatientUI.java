package com.glucoclock.database.patients_db.model;

public class PatientUI  {


	private String firstName;
    private String lastName;
    private String Email;
    private String PostCode;







    protected PatientUI() {
	}

	public PatientUI(String firstName, String lastName,String Email, String PostCode) {
		this.firstName = firstName;
		this.lastName = lastName;
        this.Email = Email;
        this.PostCode = PostCode;
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

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String toString() {
        return String.format("Patient[firstName='%s', lastName='%s']", firstName, lastName);
    }
}