package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.exeptions.DAOException;
import com.github.artemzi.hw15.realExample.pojo.City;
import com.github.artemzi.hw15.realExample.services.FactoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.artemzi.hw15.realExample.services.UtilsDAO.prepareStatement;

public class CityDAO extends DAO<City> {
    private FactoryDAO connectionManager;
    private static final String SQL_FIND_BY_ID = "SELECT * from city WHERE id = ?"; // never do stupid select with * (star) in code...
    private static final String SQL_INSERT = "INSERT INTO city VALUES (DEFAULT, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE city SET name=?, citizens=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM city WHERE id=?";

    public CityDAO(FactoryDAO connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean add(City city) {
        Object[] values = {
            city.getName(),
            city.getCitizens()
        };

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = prepareStatement(connection, SQL_INSERT, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating city failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating city failed, no generated id key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public City getById(int id) {
        return null;
    }

    @Override
    public boolean update(City student) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    protected City map(ResultSet resultSet) throws SQLException {
        return null;
    }
}
