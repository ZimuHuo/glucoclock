package com.glucoclock.database.patients_db.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Patient_db")
public class Patient implements Serializable, Comparable<Patient> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//Columns of the database and variables:
// Patient id
	@Column(name="Patientid")
	private Long patientid;

//	First Name of patient
	@Column(name = "Firstname")
	private String firstname;

//	last name of patient
	@Column(name = "Lastname")
	private String lastname;

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
	private String gender;

//	Birthday
	@Column(name = "Birthday")
	private LocalDate birthday;

//	Type of diabetes
	@Column(name = "DiabetesType")
	private String diabetestype;

//	Type(s) of insulin
//	rapid-acting insulin
	@Column(name = "RapidInsulin")
	private boolean rapidinsulin;

//	short-acting insulin
	@Column(name = "ShortInsulin")
	private boolean shortinsulin;

//	intermediate-acting insulin
	@Column(name = "IntermediateInsulin")
	private boolean intermediateinsulin;

//	long-acting insulin
	@Column(name = "LongInsulin")
	private boolean longinsulin;

//	injection method of insulin
	@Column(name = "InjectionMethod")
	private String injectionmethod;


// Constructor of patient
	public Patient(Long patientid,
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
				   String diabetesType,
				   boolean rapidInsulin,
				   boolean shortInsulin,
				   boolean intermediateInsulin,
				   boolean longInsulin,
				   String injectionMethod) {

		this.patientid=patientid;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.HomeAddressL1 = homeAddressL1;
		this.HomeAddressL2 = homeAddressL2;
		this.City = city;
		this.postCode = postCode;
		this.Phone = phone;
		this.gender = gender;
		this.birthday =  birthday;
		this.diabetestype = diabetesType;
		this.rapidinsulin = rapidInsulin;
		this.shortinsulin= shortInsulin;
		this.intermediateinsulin = intermediateInsulin;
		this.longinsulin = longInsulin;
		this.injectionmethod = injectionMethod;
	}

	public Patient() {

	}

	@Override
	public String toString() {
		return "Patient{" +
				"id=" + id +
				", firstName='" + firstname + ',' +
				", lastName='" + lastname + ',' +
				", email='" + email + ',' +
				", Address Line1='" + HomeAddressL1 + ',' +
				", Address Line2='" + HomeAddressL2 + ',' +
				", postCode='" + postCode + ',' +
				", City='" + City + ',' +
				", Phone='" + Phone + ',' +
				", Gender='" + gender + ',' +
				", Birthday=" + birthday +
				", DiabetesType='" + diabetestype + ',' +
//				", InsulinType='" + InsulinType + ',' +
				", InjectionMethod='" + injectionmethod + ',' +
				'}';
	}



	//	getters and setters

	public Long getPatientid() {
		return patientid;
	}

	public void setPatientid(Long patientid) {
		this.patientid = patientid;
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

	public String getHomeAddressL1() {return HomeAddressL1;}
	public void setHomeAddressL1(String homeAddressL1) {HomeAddressL1 = homeAddressL1;}

	public String getHomeAddressL2() {return HomeAddressL2;}
	public void setHomeAddressL2(String homeAddressL2) {HomeAddressL2 = homeAddressL2;}

	public String getCity() {return City;}
	public void setCity(String city) {City = city;}

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
	public void setBirthday(LocalDate birthday) {this.birthday = birthday;}

	public boolean isRapidInsulin() {return rapidinsulin;}
	public void setRapidInsulin(boolean rapidInsulin) {rapidinsulin = rapidInsulin;}

	public boolean isShortInsulin() {return shortinsulin;}
	public void setShortInsulin(boolean shortInsulin) {shortinsulin = shortInsulin;}

	public boolean isIntermediateInsulin() {return intermediateinsulin;}
	public void setIntermediateInsulin(boolean intermediateInsulin) {intermediateinsulin = intermediateInsulin;}

	public boolean isLongInsulin() {return longinsulin;}
	public void setLongInsulin(boolean longInsulin) {longinsulin = longInsulin;}

	public String getDiabetesType() {
		return diabetestype;
	}
	public void setDiabetesType(String diabetesType) {
		diabetestype = diabetesType;
	}

	public String getInjectionMethod() {
		return injectionmethod;
	}
	public void setInjectionMethod(String injectionMethod) {
		injectionmethod = injectionMethod;
	}


	@Override
	public int compareTo(Patient that) {
		return this.birthday.compareTo(that.birthday);
	}
}
