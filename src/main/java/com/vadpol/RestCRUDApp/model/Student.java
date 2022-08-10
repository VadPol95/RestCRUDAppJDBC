package com.vadpol.RestCRUDApp.model;

import java.util.List;
import java.util.UUID;

public class Student {
    private UUID id;
    private String fullName;
    private int yearOfAdmission;

    public Student() {
    }

    public Student(UUID id, String fullName, int yearOfAdmission) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfAdmission = yearOfAdmission;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(int yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", yearOfAdmission=" + yearOfAdmission +
                '}';
    }
}
