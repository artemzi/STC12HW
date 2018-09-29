package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.services.FactoryDAO;
import com.github.artemzi.hw15.realExample.exeptions.DAOException;
import com.github.artemzi.hw15.realExample.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.artemzi.hw15.realExample.services.UtilsDAO.prepareStatement;

public class StudentDAO extends DAO<Student> {
    private FactoryDAO connectionManager;
    private static final String SQL_FIND_BY_ID = "SELECT * from students WHERE id = ?"; // never do stupid select with * (star) in code...
    private static final String SQL_FIND_BY_CITY_ID = "SELECT * from students WHERE city_id = ?"; // never do stupid select with * (star) in code...
    private static final String SQL_INSERT = "INSERT INTO students VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE students SET name=?, family_name=?, age=?, contact=?, city_id=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM students WHERE id=?";

    public StudentDAO(FactoryDAO factory) {
        this.connectionManager = factory;
    }

    @Override
    public boolean add(Student student) {
        Object[] values = {
                student.getName(),
                student.getFamilyName(),
                student.getAge(),
                student.getContact(),
                student.getCity_id()
        };

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = prepareStatement(connection, SQL_INSERT, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating student failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating student failed, no generated id key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public Student getById(int id) {
        return find(connectionManager, SQL_FIND_BY_ID, id);
    }

    public List<Student> listByCity(int id) {
        List<Student> students = new ArrayList<>();

        try (
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = prepareStatement(connection, SQL_FIND_BY_CITY_ID, false, id);
            ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                students.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return students;
    }

    @Override
    public boolean update(Student student) {
        Object[] values = {
            student.getName(),
            student.getFamilyName(),
            student.getAge(),
            student.getContact(),
            student.getCity_id(),
            student.getId()
        };

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = prepareStatement(connection, SQL_UPDATE, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating student failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try (
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = prepareStatement(connection, SQL_DELETE, false, id);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting student failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    protected Student map(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("family_name"),
                resultSet.getInt("age"),
                resultSet.getString("contact"),
                resultSet.getInt("city_id")
        );
    }
}
