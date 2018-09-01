package com.github.artemzi.hw06.WithLock;

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
                   ++Monitor.interval;

                   Monitor.getLock().lock();
                   System.out.printf("%d seconds passed\n", seconds);
                   Monitor.getLock().unlock();

                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                    System.err.println("[TimeThread say] " + e.getMessage());
               }
            }
        }
    }
}
