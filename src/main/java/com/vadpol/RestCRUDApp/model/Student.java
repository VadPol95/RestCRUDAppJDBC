package com.vadpol.RestCRUDApp.model;

import java.util.UUID;

public class Student {
    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int yearOfAdmission;

    public Student(UUID id,
                   String firstName,
                   String lastName,
                   String patronymic,
                   int yearOfAdmission) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.yearOfAdmission = yearOfAdmission;
    }

    public Student() {
    }

    public Student(UUID id) {
        this.id = id;
    }

    public Student(UUID id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName(){
        return firstName + " " + lastName + " " + patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(int yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }
}
