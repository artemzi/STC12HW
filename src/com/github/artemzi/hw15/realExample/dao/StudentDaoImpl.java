package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.connectionManager.FactoryDAO;
import com.github.artemzi.hw15.realExample.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl implements DAO {
    private FactoryDAO connectionManager;

    public StudentDaoImpl(FactoryDAO factory) {
        this.connectionManager = factory;
    }

    @Override
    public boolean add(Student student) {

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO students VALUES (DEFAULT, ?, ?, ?, ?, ?)");
        ) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getFamilyName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getContact());
            statement.setInt(5, student.getCity_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Student getById(int id) {
        Student student = null;
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "SELECT * from students WHERE id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("family_name"),
                            resultSet.getInt("age"),
                            resultSet.getString("contact"),
                            resultSet.getInt("city_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    @Override
    public boolean update(Student student) {
        if (student.getId() != 0) {
            try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                "UPDATE students SET name=?, family_name=?, age=?, contact=?, city_id=? WHERE id=?");
            ) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getFamilyName());
                statement.setInt(3, student.getAge());
                statement.setString(4, student.getContact());
                statement.setInt(5, student.getCity_id());
                statement.setInt(6, student.getId());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM students WHERE id=?");
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByName(Student student) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM students WHERE name=?");
        ) {
            statement.setString(1, student.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
