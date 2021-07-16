package com.example.cosport.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String nickname;
    private String name;
    private String surname;
    private String gender;
    private String phone;
    private String email;
    private String occupation;
    private boolean profsportsman;
    private String city;

    public LoggedInUser(String[] userData) {
        this.nickname = userData[0];
        this.name = userData[1];
        this.surname = userData[2];
        this.gender = userData[3];
        this.phone = userData[4];
        this.email = userData[5];
        this.occupation = userData[6];
        if(userData[7].equals("true"))  this.profsportsman = true;
        else this.profsportsman = false;
        this.city = userData[8];
    }

    public String getDisplayName() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getOccupation() {
        return occupation;
    }

    public boolean isProfsportsman() {
        return profsportsman;
    }

    public String getCity() {
        return city;
    }
}