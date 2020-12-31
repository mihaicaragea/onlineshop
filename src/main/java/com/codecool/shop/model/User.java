package com.codecool.shop.model;

public class User {
    private int id;
    private final String firstName;
    private final String lastName;
    private final String country;
    private final String address;
    private final String postCode;
    private final String town;
    private final String phoneNumber;
    private final String email;
    private final String password;

    public User(String firstName, String lastName, String country, String address, String postCode,String town, String phoneNumber, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.address = address;
        this.postCode = postCode;
        this.town = town;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getTown() {
        return town;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
