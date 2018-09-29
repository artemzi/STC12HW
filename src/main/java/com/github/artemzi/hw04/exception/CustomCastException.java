package com.github.artemzi.hw04.exception;

public class CustomCastException extends ClassCastException {
    public CustomCastException(String msg) {
        super("[ObjectBox] " + msg);
    }
}
