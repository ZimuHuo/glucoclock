package com.glucoclock.views.mojo;

import java.time.LocalDate;
import java.util.Set;

public class Patient {
    private String FName, LName, Email, Home, PostCode, Phone, Gender, Diabetes;
    private LocalDate Birth;
    private Set<String> insulin, injection;

    public Patient() {

    }
}
