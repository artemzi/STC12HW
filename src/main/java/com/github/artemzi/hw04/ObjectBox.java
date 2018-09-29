package com.github.artemzi.hw04;

import com.github.artemzi.hw04.exception.CustomCastException;

import java.util.*;

public class ObjectBox<T extends Number> {
    private NavigableSet<T> storage = new TreeSet<>();

    protected ObjectBox(Object[] data) {
        for (Object d : data) {
            try {
                // first part of task. Raise exception if wrong class parent.
                this.storage.add((T) d);
            } catch (ClassCastException e) {
                throw new CustomCastException("Not an instance of Number was passed to the constructor.");
            }
        }
    }

    protected double summator() {
        double result = 0;
        for (Object current : this.storage) {
            result += getDoubleValue(current);
        }
        return result;
    }

    protected List<Double> splitter(int divider) {
        ArrayList<Double> result = new ArrayList<>();
        for (Object current : this.storage) {
            result.add(getDoubleValue(current) / divider);
        }
        return result;
    }

    // helper method for avoiding duplications
    private double getDoubleValue(Object current) {
        return (double) ((Integer) current) ;
    }

    // У класса должен быть метод addObject, добавляющий объект в коллекцию.
    protected boolean addObject(T obj) {
        boolean result = false;
        if (obj != null) {
            result = this.storage.add(obj); // if obj exists false will be returned.
        }
        return result;
    }

    // У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции.
    protected boolean deleteObject(T obj) {
        return this.storage.remove(obj); // remove if exists, as expected from method name
    }

    // Должен быть метод dump, выводящий содержимое коллекции в строку.
    protected String dump() {
        StringBuilder builder = new StringBuilder();
        for (T element : this.storage) {
            builder.append(element + ", ");
        }
        return builder.toString();
    }
}
