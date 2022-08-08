package com.vadpol.RestCRUDApp.db;

import com.vadpol.RestCRUDApp.dto.StudentDTO;
import com.vadpol.RestCRUDApp.model.StudentCreate;
import com.vadpol.RestCRUDApp.exception.UserNotFoundException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class DBService {

    public static final String driverClassName = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/hibernate";
    public static final String user = "postgres";
    public static final String password = "rootroot";


    private Connection getConnection() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource.getConnection();
    }

    public List<StudentDTO> getAll() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Студенты");

        List<StudentDTO> student = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("ФИО");
            int yearOfAdmission = rs.getInt("Год_поступления");
            student.add(new StudentDTO(id, fullName, yearOfAdmission));
        }
        connection.close();
        return student;
    }


    public StudentDTO getById(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL = "select * from Студенты where id = " + id;
        ResultSet rs = statement.executeQuery(SQL);

        List<StudentDTO> student = new ArrayList<>();
        while (rs.next()) {
            String fullName = rs.getString("ФИО");
            int yearOfAdmission = rs.getInt("Год_поступления");
            student.add(new StudentDTO(id, fullName, yearOfAdmission));
        }
        connection.close();

        if (student.isEmpty()) {
            throw new UserNotFoundException(id);
        } else {
            return student.get(0);
        }
    }


    public StudentDTO getName(String name) throws SQLException {
        StringBuilder sbName = new StringBuilder();
        sbName.append("'").append(name).append("'");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL = "select * from Студенты where ФИО = " + sbName;
        ResultSet rs = statement.executeQuery(SQL);

        List<StudentDTO> student = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("ФИО");
            int yearOfAdmission = rs.getInt("Год_поступления");
            student.add(new StudentDTO(id, fullName, yearOfAdmission));
        }
        connection.close();

        if (student.isEmpty()) {
            throw new UserNotFoundException(name);
        } else {
            return student.get(0);
        }
    }

    public void addStudent(StudentCreate student) throws SQLException {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Студенты(ФИО,Год_поступления) VALUES(?,?)");
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setInt(2, student.getYearOfAdmission());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateStudent(StudentDTO student, int id) throws SQLException {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Студенты SET ФИО=?,Год_поступления=? WHERE id=?");

            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setInt(2, student.getYearOfAdmission());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Студенты WHERE id=?");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
