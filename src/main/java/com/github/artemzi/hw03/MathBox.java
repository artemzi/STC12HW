package com.github.artemzi.hw03;

import java.util.*;
import java.util.stream.Collectors;

class MathBox {

    private NavigableSet<Integer> storage = new TreeSet<>();

    MathBox(Integer[] data) {
        this.storage.addAll(Arrays.asList(data));
    }

    int summator() {
        return this.storage.stream().mapToInt(Integer::intValue).sum();
    }

    ArrayList<Double> splitter(int divider) {
        ArrayList<Double> result = new ArrayList<>(this.storage.size());
        result.addAll(this.storage
                .stream()
                .map(e -> (double) e) // convert type
                .collect(Collectors.toList()));
        result.replaceAll(e -> e / divider);
        return result;
    }

    /**
     * Method return true if succeed or false
     * @param  digit, object for removing
     * @return boolean
    * */
    boolean removeElementIfExists(Integer digit) {
        // remove, is a method from a collection and it already contains all necessary checks
        return this.storage.remove(digit);
    }

    @Override
    public boolean equals(Object o) { // TODO: does current code satisfying contract?
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
