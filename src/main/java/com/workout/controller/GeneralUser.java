package com.workout.controller;

import java.time.LocalDate;


public class GeneralUser {

    private String firstName;
    private String lastName;
    private final LocalDate registeredDate; // YYYY-MM-DD

    public GeneralUser(String firstName, String lastName, LocalDate registeredDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredDate = LocalDate.now(); //Setting it permanently directly
    }

    // Getters and Setters
    public String getLastName() {
        return lastName;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}