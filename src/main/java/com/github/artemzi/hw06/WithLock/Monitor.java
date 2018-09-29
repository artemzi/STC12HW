package com.github.artemzi.hw06.WithLock;

import java.util.concurrent.locks.ReentrantLock;

class Monitor {
    volatile static int interval = 0;
    private static ReentrantLock lock = new ReentrantLock();

    static ReentrantLock getLock() {
        return lock;
    }
}
