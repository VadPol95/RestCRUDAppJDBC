package com.vadpol.RestCRUDApp.repository.impl;

import com.vadpol.RestCRUDApp.db.DBService;
import com.vadpol.RestCRUDApp.dto.StudentDTO;
import com.vadpol.RestCRUDApp.model.StudentCreate;
import com.vadpol.RestCRUDApp.repository.StudentService;
import org.springframework.stereotype.Service;

import java.sql.*;


@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void getAll() throws SQLException {
        DBService dbService = new DBService();
        dbService.getAll();
    }

    @Override
    public void getById(int id) throws SQLException {
        DBService dbService = new DBService();
        dbService.getById(id);
    }

    @Override
    public void getName(String name) throws SQLException {
        DBService dbService = new DBService();
        dbService.getName(name);
    }

    @Override
    public int addStudent(StudentCreate student) throws SQLException {
        DBService dbService = new DBService();
        dbService.addStudent(student);
        return 0;
    }

    @Override
    public void updateStudent(StudentDTO student, int id) throws SQLException {
        DBService dbService = new DBService();
        dbService.updateStudent(student, id);
    }

    @Override
    public void delete(int id) throws SQLException {
        DBService dbService = new DBService();
        dbService.delete(id);
    }

}
