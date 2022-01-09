package com.glucoclock.database.patients_db.model;


import com.glucoclock.database.Person.Person;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Patient_db")
public class Patient extends Person implements Comparable<Patient>{

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

	@Column(name = "logbooktype")
	private String logbooktype;



// Constructor of patient
	public Patient(UUID uid,
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
		this.shortinsulin = shortInsulin;
		this.intermediateinsulin = intermediateInsulin;
		this.longinsulin = longInsulin;
		this.injectionmethod = injectionMethod;
		this.uid = uid;
		this.logbooktype = "N/A";

	}

	public Patient() {

	}

	public String getLogbooktype() {
		return logbooktype;
	}

	public void setLogbooktype(String logbooktype) {
		this.logbooktype = logbooktype;
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

	public boolean isMale(){
		return this.gender.equals("Male");
	}
	public boolean isFemale(){
		return this.gender.equals("Female");
	}
	public boolean isType1(){
		return  this.diabetestype.equals("Type I");
	}
	public boolean isType2(){
		return  this.diabetestype.equals("Type II");
	}
	public boolean isGestational(){
		return this.diabetestype.equals("Gestational");
	}
	@Override
	public int compareTo(Patient that) {
		return this.birthday.compareTo(that.birthday);
	}
}

//	UUID uid,
//	LocalDateTime dateTime,
//	Boolean isFrequentUrination,
//	Boolean isExcessiveThirst,
//	Boolean isUnexplainedWeightLoss,
//	Boolean isExtremeHunger,
//	Boolean isSuddenVisionChanges,
//	Boolean isTinglingOrNumbnessInHandsOrFeet,
//	Boolean isVeryDrySkin,
//	Boolean isSoresThatAreSlowToHeal,
//	Boolean isMoreInfectionsThanUsual