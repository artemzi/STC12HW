package com.github.artemzi.lab01.utils;

public class WaitGroup {
    // Number of jobs in WaitGroup
    private int jobs = 0;

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
}
