package com.github.artemzi.hw15.realExample.exeptions;

public class DAOException extends RuntimeException {
    public DAOException(String message) {
        super(message);
}

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
