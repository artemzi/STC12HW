package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.connectionManager.FactoryDAO;
import com.github.artemzi.hw15.realExample.exeptions.DAOException;
import com.github.artemzi.hw15.realExample.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.artemzi.hw15.realExample.connectionManager.UtilsDAO.prepareStatement;

public class StudentDaoImpl implements DAO {
    private FactoryDAO connectionManager;
    private static final String SQL_FIND_BY_ID = "SELECT * from students WHERE id = ?"; // never do stupid select with * (star) in code...
    private static final String SQL_INSERT = "INSERT INTO students VALUES (DEFAULT, ?, ?, ?, ?, ?)";

    public StudentDaoImpl(FactoryDAO factory) {
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
        return find(SQL_FIND_BY_ID, id);
    }

    /**
     * Method can be used with any sql query.
     * just pass required sql and give expected params
     *
     * @param sql query
     * @param values query params
     * @return Student
     */
    private Student find(String sql, Object... values) {
        Student student = null;
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = prepareStatement(connection, sql, false, values);
            ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                student = map(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    private static Student map(ResultSet resultSet) throws SQLException {
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
