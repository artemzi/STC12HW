package com.github.artemzi.hw06.WithLock;

public class Worker implements Runnable {
    private final int interval, id;
    private String message;

    Worker(int interval, String message, int threadId) {
        this.interval = interval;
        this.id = threadId;
        this.message = message;

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            if(Monitor.interval % interval == 0) {
                Monitor.getLock().lock();
                Message.write(message, this.id);
                Monitor.getLock().unlock();

                try { // give TimeThread time for taking the lock
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Message {
        static void write(String text, int id) {
            System.out.printf("[interval value=%d] %s%d\n", Monitor.interval, text, id);
        }
    }
}
