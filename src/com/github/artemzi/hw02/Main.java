package com.github.artemzi.hw02;

public class Main {
    public static void main(String[] args) {
        MathBox mathBox = new MathBox();

        try {
            mathBox.factorial(123);
        } catch (StackOverflowError e) {
            System.err.println("[factorial]: StackOverflowError.");
        }

        try {
            mathBox.divider(3123, 0);
        } catch (HW01Exception e) {
            System.err.println("[divider]: " + e.getMessage());
        }

        mathBox.dividerExceptionInside(3123, 0);

        System.out.println("\nAll calculations finished.");
    }
}
