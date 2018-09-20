package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.exeptions.DAOConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDAO {
    private static final String PROPERTIES_FILE = "hw15.properties";
    private static final Properties PROPERTIES = new Properties();
    private String specificKey;

    public PropertiesDAO(String specificKey) throws DAOConfigurationException {
        this.specificKey = specificKey;
    }

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException(
                "Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException(
                "Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    public String getProperty(String key, boolean mandatory) throws DAOConfigurationException {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DAOConfigurationException("Required property '" + fullKey + "'"
                    + " is missing in properties file '" + PROPERTIES_FILE + "'.");
            } else {
                // Make empty value null. Empty Strings are evil.
                property = null;
            }
        }

        return property;
    }
}
