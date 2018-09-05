package com.github.artemzi.lab01;

import com.github.artemzi.lab01.lib.TypeConverter;
import com.github.artemzi.lab01.lib.WaitGroup;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Content implemented as thread safe singleton.
 *
 * Used for storing received data.
 */
public class Content extends WaitGroup {
    private Set<Byte[]> data;
    private Content() {
        this.data = Collections.newSetFromMap(new ConcurrentHashMap<Byte[], Boolean>());
    }

    private static class ContentHolder {
        private static final Content INSTANCE = new Content();
    }

    // Classic implementation (with private instance field check and synchronized block in getInstance())
    // can fail in certain scenarios where too many threads try to get
    // the instance of the Singleton class simultaneously.
    // Current implementation (Bill Pugh Singleton implementation) helps avoid it.
    public static Content getInstance() {
        return ContentHolder.INSTANCE;
    }

    public Set<Byte[]> getData() {
        return data;
    }

    public boolean addValue(byte[] val) {
        return this.data.add(TypeConverter.toObjects(val));
    }
}
