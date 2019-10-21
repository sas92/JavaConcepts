package com.sas.sample;

/**
 * Reference: https://www.geeksforgeeks.org/synchronized-in-java/
 */

class Sender {
    public void send(String msg) throws InterruptedException {
        System.out.println("Sending " + msg);
        Thread.sleep(1000);
        System.out.println("Sent " + msg);
    }
}

class ThreadedSend extends Thread {
    private String msg;
    private Sender sender;

    public ThreadedSend(String msg, Sender sender) {
        this.msg = msg;
        this.sender = sender;
    }

    @Override
    public void run() {
        synchronized (sender) {
            try {
                sender.send(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Sender sender = new Sender();
        ThreadedSend ts1 = new ThreadedSend("Hello", sender);
        ThreadedSend ts2 = new ThreadedSend("Goodbye", sender);

        ts1.start();
        ts2.start();

        ts1.join();
        ts2.join();
    }
}
