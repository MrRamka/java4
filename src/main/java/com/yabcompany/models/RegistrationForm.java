package com.yabcompany.models;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import java.sql.Date;

public class RegistrationForm {

    @Email
    private String email;

    @Length(min = 8, max = 32)
    private String password;

    @Length(min = 8, max = 32)
    private String confirmPassword;

    @Length(max = 32)
    private String country;

    private boolean agreement;

    private boolean gender;

    private Date birthday;


    public RegistrationForm(@Email String email,
                            @Length(min = 8, max = 32) String password,
                            @Length(min = 8, max = 32) String confirmPassword,
                            @Length(max = 32) String country,
                            boolean agreement, boolean gender,
                            Date birthday) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.country = country;
        this.agreement = agreement;
        this.gender = gender;
        this.birthday = birthday;
    }

    public RegistrationForm() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public boolean getGender() {
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
        return "RegistrationForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", country='" + country + '\'' +
                ", agreement=" + agreement +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
