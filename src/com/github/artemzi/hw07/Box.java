package com.github.artemzi.hw07;

import java.util.List;

/**
 * Interface will be used in proxy
 */
public interface Box<T> {
    int summator();
    List<Double> splitter(int divider);
    boolean removeElementIfExists(Integer digit);

}
