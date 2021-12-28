package com.glucoclock.database.user_db;


import org.dom4j.tree.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "User_db")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;

    @Column(name = "uid")
    //private String uid;
    private UUID uid;

    public User(String email, String password, Role role, UUID uid) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.uid = uid;
    }

    public User() {

    }

//    @Override
//    public String toString() {
//        return "Patient{" +
//                "id=" + id +
//                ", firstName='" + firstName + ',' +
//                ", lastName='" + lastName + ',' +
//                ", email='" + email + ',' +
//                ", HomeAddress='" + HomeAddress + ',' +
//                ", postCode='" + postCode + ',' +
//                ", Phone='" + Phone + ',' +
//                ", Gender='" + Gender + ',' +
//                ", Birthday=" + Birthday +
//                ", DiabetesType='" + DiabetesType + ',' +
//                ", InsulinType='" + InsulinType + ',' +
//                ", InjectionMethod='" + InjectionMethod + ',' +
//                '}';
//    }

    //	getters and setters
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getUid() {return uid;}
    public void setUid(UUID uid) {
        this.uid = uid;
    }

}
