package com.github.artemzi.hw02;

public class MathBox implements Box {

    @Override
    public int summa(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public int diff(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public long factorial(Integer a) throws StackOverflowError {
        // There is no break condition, expect exception
        return a * factorial(a - 1);
    }

    @Override
    public double dividerExceptionInside(Integer a, Integer b) {
        double result = 0;
        try {
            result = (double) (a / b);
            return result;
        } catch (ArithmeticException e) {
            System.err.println("[dividerExceptionInside] wtf?: It's ok, try one more time.");
        }

        return a;
    }

    @Override
    public double divider(Integer a, Integer b) throws HW01Exception {
        try {
            return (double) (a / b);
        } catch (ArithmeticException e) {
            throw new HW01Exception("I'm new ArithmeticException");
        }
    }
}
