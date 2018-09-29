package com.github.artemzi.hw06;

import java.util.concurrent.TimeUnit;

public class TimeThread implements Runnable {
    private long startTime;

    TimeThread(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void run() {
        synchronized (this) {
            while(true) {
                long seconds = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime) + 1;

               try {
                   synchronized (Monitor.class) {
                       ++Monitor.interval;
                       System.out.printf("%d seconds passed\n", seconds);
                       Monitor.class.notifyAll();
                   }
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                    System.err.println("[TimeThread say] " + e.getMessage());
               }
            }
        }
    }
}
