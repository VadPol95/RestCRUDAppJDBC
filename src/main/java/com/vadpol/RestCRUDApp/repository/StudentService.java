package com.vadpol.RestCRUDApp.repository;

import com.vadpol.RestCRUDApp.dto.StudentDTO;
import com.vadpol.RestCRUDApp.model.StudentCreate;

import java.sql.SQLException;

public interface StudentService {

    void getAll() throws SQLException;
    void getById(int id) throws SQLException;
    void getName(String name) throws SQLException;
    int addStudent(StudentCreate student) throws SQLException;
    void updateStudent(StudentDTO student, int id) throws SQLException;
    void delete(int id) throws SQLException;
}
