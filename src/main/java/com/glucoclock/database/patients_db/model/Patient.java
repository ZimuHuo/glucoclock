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


	//Columns of the database and variables:
//	First Name of patient
	@Column(name = "firstname")
	private String firstName;

//	last name of patient
	@Column(name = "lastname")
	private String lastName;

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

//	Type of diabetes
	@Column(name = "DiabetesType")
	private String DiabetesType;

//	Type(s) of insulin
//	rapid-acting insulin
	@Column(name = "RapidInsulin")
	private boolean RapidInsulin;

//	short-acting insulin
	@Column(name = "ShortInsulin")
	private boolean ShortInsulin;

//	intermediate-acting insulin
	@Column(name = "IntermediateInsulin")
	private boolean IntermediateInsulin;

//	long-acting insulin
	@Column(name = "LongInsulin")
	private boolean LongInsulin;

//	injection method of insulin
	@Column(name = "InjectionMethod")
	private String InjectionMethod;


// Constructor of patient
	public Patient(String firstName,
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

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.HomeAddressL1 = homeAddressL1;
		this.HomeAddressL2 = homeAddressL2;
		this.City = city;
		this.postCode = postCode;
		this.Phone = phone;
		this.Gender = gender;
		this.Birthday =  birthday;
		this.DiabetesType = diabetesType;
		this.RapidInsulin = rapidInsulin;
		this.ShortInsulin = shortInsulin;
		this.IntermediateInsulin = intermediateInsulin;
		this.LongInsulin = longInsulin;
		this.InjectionMethod = injectionMethod;
	}

	public Patient() {

	}

	@Override
	public String toString() {
		return "Patient{" +
				"id=" + id +
				", firstName='" + firstName + ',' +
				", lastName='" + lastName + ',' +
				", email='" + email + ',' +
				", Address Line1='" + HomeAddressL1 + ',' +
				", Address Line2='" + HomeAddressL2 + ',' +
				", postCode='" + postCode + ',' +
				", City='" + City + ',' +
				", Phone='" + Phone + ',' +
				", Gender='" + Gender + ',' +
				", Birthday=" + Birthday +
				", DiabetesType='" + DiabetesType + ',' +
//				", InsulinType='" + InsulinType + ',' +
				", InjectionMethod='" + InjectionMethod + ',' +
				'}';
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

	public boolean isRapidInsulin() {return RapidInsulin;}
	public void setRapidInsulin(boolean rapidInsulin) {RapidInsulin = rapidInsulin;}

	public boolean isShortInsulin() {return ShortInsulin;}
	public void setShortInsulin(boolean shortInsulin) {ShortInsulin = shortInsulin;}

	public boolean isIntermediateInsulin() {return IntermediateInsulin;}
	public void setIntermediateInsulin(boolean intermediateInsulin) {IntermediateInsulin = intermediateInsulin;}

	public boolean isLongInsulin() {return LongInsulin;}
	public void setLongInsulin(boolean longInsulin) {LongInsulin = longInsulin;}

	public String getDiabetesType() {
		return DiabetesType;
	}
	public void setDiabetesType(String diabetesType) {
		DiabetesType = diabetesType;
	}

	public String getInjectionMethod() {
		return InjectionMethod;
	}
	public void setInjectionMethod(String injectionMethod) {
		InjectionMethod = injectionMethod;
	}



}
