package com.github.artemzi.lab01;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Content implemented as thread safe singleton.
 *
 * Used for storing received data.
 */
public class Content {
    private Set<Byte[]> data;
    // Number of jobs in WaitGroup
    private int jobs = 0;
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
        return this.data.add(toObjects(val));
    }

    // Increment jobs counter
    public synchronized void add(int i) {
        jobs += i;
    }

    // Decrement jobs counter and free resource if finished
    public synchronized void done() {
        if (--jobs == 0) {
            notifyAll();
        }
    }

    // Wait before jobs done
    public synchronized void await() throws InterruptedException {
        while (jobs > 0) {
            wait();
        }
    }

    // TODO: do i need it here?
    private Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }

    // TODO: do i ever need it?
    public byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];

        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;
    }
}
