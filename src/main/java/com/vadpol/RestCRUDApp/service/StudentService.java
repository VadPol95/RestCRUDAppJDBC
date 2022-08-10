package com.vadpol.RestCRUDApp.service;

import com.vadpol.RestCRUDApp.dto.StudentDTO;
import com.vadpol.RestCRUDApp.model.StudentCreate;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {

    void getAll() throws SQLException;
    void getById(List<Integer> id) throws SQLException;
    void getName(String name) throws SQLException;
    int addStudent(StudentCreate student) throws SQLException;
    void updateStudent(StudentDTO student, int id) throws SQLException;
    void delete(int id) throws SQLException;
}
