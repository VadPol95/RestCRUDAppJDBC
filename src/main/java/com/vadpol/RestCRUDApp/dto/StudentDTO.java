package com.vadpol.RestCRUDApp.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String fullName;
    private int yearOfAdmission;

    public StudentDTO(int id, String fullName, int yearOfAdmission) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfAdmission = yearOfAdmission;
    }


    public String getFullName() {
        return fullName;
    }
}
