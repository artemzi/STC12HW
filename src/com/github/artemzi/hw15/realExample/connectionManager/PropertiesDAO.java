package com.github.artemzi.hw15.realExample.connectionManager;

import com.github.artemzi.hw15.realExample.exeptions.ConfigurationExceptionDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesDAO used for load configuration parameters from properties file
 */
public class PropertiesDAO {
    private static final String PROPERTIES_FILE = "hw15.properties";
    private static final Properties PROPERTIES = new Properties();
    private String specificKey;

    public PropertiesDAO(String specificKey) throws ConfigurationExceptionDAO {
        this.specificKey = specificKey;
    }

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new ConfigurationExceptionDAO(
                "Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new ConfigurationExceptionDAO(
                "Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    public String getProperty(String key, boolean mandatory) throws ConfigurationExceptionDAO {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new ConfigurationExceptionDAO("Required property '" + fullKey + "'"
                    + " is missing in properties file '" + PROPERTIES_FILE + "'.");
            } else {
                // Make empty value null. Empty Strings are evil.
                property = null;
            }
        }

        return property;
    }
}
