package com.yabcompany.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.yabcompany.api.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class MainUser implements Serializable {

    @JsonView(Views.Public.class)
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonView(Views.Public.class)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonView(Views.Public.class)
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "agreement", nullable = false)
    private boolean agreement;

    @JsonView(Views.Public.class)
    @Column(name = "gender", nullable = false)
    private boolean gender;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    public MainUser() {
    }

    public MainUser(long id,
                    String email,
                    String password,
                    String country,
                    boolean agreement,
                    boolean gender,
                    Date birthday) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.country = country;
        this.agreement = agreement;
        this.gender = gender;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "MainUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", agreement=" + agreement +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
