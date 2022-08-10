package com.vadpol.RestCRUDApp.controller;

import com.vadpol.RestCRUDApp.db.DBService;
import com.vadpol.RestCRUDApp.dto.StudentDTO;
import com.vadpol.RestCRUDApp.model.Student;
import com.vadpol.RestCRUDApp.model.StudentCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер
 */
@RestController
@RequestMapping("/api")
public class StudentController {
    private DBService service;

    @Autowired
    public StudentController(DBService service) {
        this.service = service;
    }

    @GetMapping("/student")
    List<StudentDTO> getStudents() throws SQLException {
        return service.getAll();
    }

    @GetMapping("students/{fullName}")
    List<StudentDTO> getName(@PathVariable String fullName) throws SQLException {
        return service.getName(fullName);
    }

    @GetMapping("student/{id}")
    List<StudentDTO> getStudent(@PathVariable("id") List<Integer> ids) throws SQLException {
        return service.getByIds(ids);
    }

    @PostMapping("/student")
    public String create(@RequestBody StudentCreate student) throws SQLException {
        service.addStudent(student);
        return "OK";
    }

    @PutMapping("/{id}")
    Student updateStudent(@RequestBody StudentDTO student, @PathVariable("id") int id) throws SQLException {
        service.updateStudent(student, id);
        return new Student();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        service.delete(id);
        return "OK";
    }

}
