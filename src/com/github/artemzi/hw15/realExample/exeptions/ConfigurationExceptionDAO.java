package com.github.artemzi.hw15.realExample.exeptions;

public class ConfigurationExceptionDAO extends RuntimeException {

    public ConfigurationExceptionDAO(String message) {
        super(message);
    }

    public ConfigurationExceptionDAO(Throwable cause) {
        super(cause);
    }

    public ConfigurationExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }
}
