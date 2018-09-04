package com.github.artemzi.hw08.exec;

import bsh.EvalError;
import bsh.Interpreter;

public interface Executor {
    static void execute(String code) {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.eval(code);
        } catch (EvalError e) {
            System.err.println(e.getMessage());
        }
    }
}
