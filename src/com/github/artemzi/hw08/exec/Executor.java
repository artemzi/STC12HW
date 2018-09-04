package com.github.artemzi.hw08.exec;

import bsh.EvalError;
import bsh.Interpreter;

import java.io.IOException;

public interface Executor {
    static void execute(String code) {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.eval(code);
        } catch (EvalError e) {
            System.err.println(e.getMessage());
        }
    }

    static void executeFromFile(String filePath) {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.source(filePath);
        } catch (IOException | EvalError e) {
            System.err.println(e.getMessage());
        }
    }
}
