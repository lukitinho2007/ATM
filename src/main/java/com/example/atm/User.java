package com.example.atm;
public class User {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String pinCode;

    public User(String firstName, String lastName, String cardNumber, String pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }
}