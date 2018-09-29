package com.github.artemzi.hw06.WithLock;

/**
 * Бонус: Пакет Concurrent, перевод примеров на Lock  вместо синхронизации через wait-notify
 */
public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Thread timer = new Thread(new TimeThread(startTime));
        timer.start();

        new Worker(5, "Thread id=", 1);
        new Worker(7, "Thread id=", 2);
    }
}
