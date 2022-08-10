package com.vadpol.RestCRUDApp.db;

import com.vadpol.RestCRUDApp.dto.StudentDTO;
import com.vadpol.RestCRUDApp.model.StudentCreate;
import com.vadpol.RestCRUDApp.exception.UserNotFoundException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Подключение к БД + запросы к БД
 * getAll() - Получить список всех студентов
 * getById() - Получить студента по ID
 * getByName() - Получить студента по имени
 * addStudent() - Добавить студента в БД
 * updateStudent() - Изменить данные о студенте в БД
 * delete() - Удалить студента из БД
 */

@Service
public class DBService {

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/hibernate";
    private static final String USER = "postgres";
    private static final String PASSWORD = "rootroot";


    private Connection getConnection() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource.getConnection();
    }

    public List<StudentDTO> getAll() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Student");

        List<StudentDTO> student = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("fullname");
            int yearOfAdmission = rs.getInt("year_of_admission");
            student.add(new StudentDTO(id, fullName, yearOfAdmission));
        }
        connection.close();
        return student;
    }

    public List<StudentDTO> getByIds(List<Integer> ids) throws SQLException {
        Connection connection = getConnection();
        StringBuilder sbSql = new StringBuilder(1024);
        sbSql.append("SELECT * FROM Student WHERE id IN(");

        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) sbSql.append(",");
            sbSql.append(" ?");
        }
        sbSql.append(" )");
        PreparedStatement pst = connection.prepareStatement(String.valueOf(sbSql));

        for (int i = 0; i < ids.size(); i++) {
            pst.setInt(i + 1, ids.get(i));
        }

        ResultSet rs = pst.executeQuery();
        List<StudentDTO> student = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("fullname");
            int yearOfAdmission = rs.getInt("year_of_admission");
            student.add(new StudentDTO(id, fullName, yearOfAdmission));
        }
        connection.close();

        if (student.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(ids));
        } else {
            return student;
        }
    }

    public List<StudentDTO> getName(String name) throws SQLException {
        StringBuilder sbName = new StringBuilder();
        sbName.append("'").append(name).append("'");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String SQL = "select * from Student where fullname = " + sbName;
        ResultSet rs = statement.executeQuery(SQL);

        List<StudentDTO> student = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("fullname");
            int yearOfAdmission = rs.getInt("year_of_admission");
            student.add(new StudentDTO(id, fullName, yearOfAdmission));
        }
        connection.close();

        if (student.isEmpty()) {
            throw new UserNotFoundException(name);
        } else {
            return student;
        }
    }

    public void addStudent(StudentCreate student) throws SQLException {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Student (fullname,year_of_admission) VALUES(?,?)");
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Student SET fullname=?,year_of_admission=? WHERE id=?");

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
            preparedStatement = connection.prepareStatement("DELETE FROM Student WHERE id=?");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
