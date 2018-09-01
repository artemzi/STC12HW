package com.github.artemzi.hw02;

/**
 * Разработать класс MathBox со следующими методами:
 *
 * public int summa (Integer a, Integer b);
 *
 * public int diff (Integer a, Integer b);
 *
 * public long factorial (Integer a);
 *
 * public double dividerExceptionInside(Integer a, Integer b);
 *
 * public double divider (Integer a, Integer b) throws Exception)
 *
 * В реализации метода factorial должна быть ошибка, приводящая к OutOfMemory, или StackOverflow.
 * Методы, выполняющие деление, в одном случае должны не допускать появление исключения при делении на 0, в другом
 * пробрасывать его (исходя из сигнатуры метода).
 *
 * Опционально:  поменять сигнатуру метода, пробрасывающего исключение так, чтобы он бросал собственный тип исключения.
 * Описать это исключение в отдельном классе.
 */
public interface Box {
    public int summa (Integer a, Integer b);

    public int diff (Integer a, Integer b);

    public long factorial (Integer a) throws Exception;

    public double dividerExceptionInside(Integer a, Integer b);

    public double divider (Integer a, Integer b) throws Exception;
}
