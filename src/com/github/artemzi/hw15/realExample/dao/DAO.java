package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.services.FactoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.artemzi.hw15.realExample.services.UtilsDAO.prepareStatement;

public abstract class DAO<T> {
    public abstract boolean add(T student);

    public abstract T getById(int id);

    public abstract boolean update(T student);

    public abstract boolean deleteById(int id);

    protected abstract T map(ResultSet resultSet) throws SQLException;

    /**
     * Method can be used with any sql SELECT query.
     * just pass required sql and give expected params
     *
     * @param sql query
     * @param values query params
     * @return Student
     */
    protected T find(FactoryDAO connectionManager, String sql, Object... values) {
        T obj = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = prepareStatement(connection, sql, false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                obj = map(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
