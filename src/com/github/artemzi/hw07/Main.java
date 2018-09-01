package com.github.artemzi.hw07;

import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Работаем с проектом из Урока 3
 *
 * 1.      Создать прокси для класса mathBox (подумайте, что для этого надо добавить к классу)
 *
 * 2.      В вызов методов класса через прокси добавить логирование в консоль при использовании аннотации Logged
 *
 * 3.      В вызов методов класса через прокси добавить очистку поля-контейнера при использовании аннотации ClearData у вызванного метода
 *
 * Нужные аннотации добавить к проекту
 */
public class Main {
    public static void main(String[] args) {
        Integer[] data = new Integer[1000];
        for (int i = 0; i < 1000; i++) {  // fill data with random ints
            data[i] = ThreadLocalRandom.current().nextInt(1, 100500);
        }

        MathHandler handler = new MathHandler(new MathBox(data));
        Box box = (Box) Proxy.newProxyInstance(MathHandler.class.getClassLoader(),
                MathBox.class.getInterfaces(),
                handler);

        // If ClearData annotation works, we cannot get sum of empty collection
        System.out.println(box.summator());
        box.splitter(123);
        Random random = new Random();
        box.removeElementIfExists(data[random.nextInt(100)]);
    }
}
