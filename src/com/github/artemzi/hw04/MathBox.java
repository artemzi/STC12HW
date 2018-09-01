package com.github.artemzi.hw04;

import com.github.artemzi.hw04.ObjectBox;

import java.util.*;

public class MathBox extends ObjectBox {
    private NavigableSet<Integer> storage = new TreeSet<>();

    public MathBox(Integer[] data) {
        super(data);
    }

    public boolean removeElementIfExists(Integer digit) { // For now method can be removed and used from parent
        return super.deleteObject(digit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        if (this.storage.hashCode() != mathBox.hashCode()) return false;
        return Objects.equals(this.storage, mathBox.storage);
    }

    @Override
    public int hashCode() {
        return this.storage.hashCode();
    }

    @Override
    public String toString() {
        return "MathBox{" +
                this.storage +
                '}';
    }
}
