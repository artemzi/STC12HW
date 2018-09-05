package com.github.artemzi.lab01.exceptions;

public class CannotAddContentException extends Exception {
    private final String MESSAGE;

    public CannotAddContentException(String msg) {
        this.MESSAGE = msg;
    }

    @Override
    public String getMessage() {
        return this.MESSAGE;
    }
}
