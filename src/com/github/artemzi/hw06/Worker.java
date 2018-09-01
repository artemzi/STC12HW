package com.github.artemzi.hw06;

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
            synchronized (Monitor.class) {
                try {
                    Monitor.class.wait();
                    if(Monitor.interval % interval == 0) {
                        Message.write(message, this.id);
                    }
                } catch (InterruptedException e) {
                    System.err.println("[Worker say] " + e.getMessage());
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
