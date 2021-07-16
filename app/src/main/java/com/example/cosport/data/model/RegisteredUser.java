package com.example.cosport.data.model;

import androidx.annotation.Nullable;

public class RegisteredUser {

    private String username;
    private String firstname;
    private String surname;
    private String password;
    private String email;
    private String city;
    private String phone;
    private String socialNetwork;
    private String occupation;
    private String gender;
    private String profSportsman;

    public RegisteredUser(String username, String password,
                          String firstname, String surname, String gender,
                          String phone,String email, String occupation,
                          String profSportsman, String city, String socialNetwork){
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.city = city;
        this.phone = phone;
        this.socialNetwork = socialNetwork;
        this.occupation = occupation;
        this.gender = gender;
        this.profSportsman = profSportsman;
    }


    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }



    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }


    public String getPhone() {
        return phone;
    }


    public String getSocialNetwork() {
        return socialNetwork;
    }



    public String getOccupation() {
        return occupation;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }
}
