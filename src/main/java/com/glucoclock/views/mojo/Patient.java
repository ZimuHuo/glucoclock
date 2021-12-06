package com.glucoclock.views.mojo;

import java.time.LocalDate;
import java.util.Set;

public class Patient {
    private String FName, LName, Email, Home, PostCode, Phone, Gender, Diabetes;
    private LocalDate Birth;
    private Set<String> insulin, injection;

    public Patient() {

    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
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

    public String getDiabetes() {
        return Diabetes;
    }

    public void setDiabetes(String diabetes) {
        Diabetes = diabetes;
    }

    public LocalDate getBirth() {
        return Birth;
    }

    public void setBirth(LocalDate birth) {
        Birth = birth;
    }

    public Set<String> getInsulin() {
        return insulin;
    }

    public void setInsulin(Set<String> insulin) {
        this.insulin = insulin;
    }

    public Set<String> getInjection() {
        return injection;
    }

    public void setInjection(Set<String> injection) {
        this.injection = injection;
    }
}
