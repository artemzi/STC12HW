package com.github.artemzi.hw08.exec;

import static com.github.artemzi.hw08.exec.Executor.execute;

public class Execute {
    public static void main(String[] args) {
        System.out.print("Print string expression: ");
        execute("System.out.println(\"It works!\")");

        System.out.print("Call stdlib function: ");
        execute("System.out.println(Math.pow(2, 2));");
    }
}
