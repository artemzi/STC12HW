package com.github.artemzi.hw15.realExample.connectionManager;

import com.github.artemzi.hw15.realExample.exeptions.ConfigurationExceptionDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Create connection contract based on provided options
 */
public abstract class FactoryDAO {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    public static FactoryDAO getInstance(String name) throws ConfigurationExceptionDAO {
        if (name == null) {
            throw new ConfigurationExceptionDAO("Database name is null.");
        }

        PropertiesDAO properties = new PropertiesDAO(name);
        String url = properties.getProperty(PROPERTY_URL, true);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
        String password = properties.getProperty(PROPERTY_PASSWORD, false);
        String username = properties.getProperty(PROPERTY_USERNAME, password != null);

        try { // check if correct driver was loaded
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new ConfigurationExceptionDAO(
                "Driver class '" + driverClassName + "' is missing in classpath.", e);
        }

        return new ConnectionManager(url, username, password);
    }

    public abstract Connection getConnection() throws SQLException;
}
