package com.sas.sample;

/**
 * Reference: https://www.javatpoint.com/volatile-keyword-in-java
 */

class VolatileData {
    private volatile int counter = 0;
    //private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        ++counter;
    }
}

class VolatileThread extends Thread {
    private final VolatileData volatileData;

    public VolatileThread(VolatileData volatileData) {
        this.volatileData = volatileData;
    }

    @Override
    public void run() {
        int oldValue = volatileData.getCounter();
        System.out.println("Thread[" + Thread.currentThread().getId() + "]: Old value= " + oldValue);
        volatileData.increaseCounter();
        int newValue = volatileData.getCounter();
        System.out.println("Thread[" + Thread.currentThread().getId() + "]: New value= " + newValue);
    }
}

public class VolatileDemo {
    private final static int noOfThreads = 2;

    public static void main(String[] args) throws InterruptedException {
        VolatileData volatileData = new VolatileData();
        Thread[] threadList = new Thread[noOfThreads];
        for (int i = 0; i < noOfThreads; ++i)
            threadList[i] = new VolatileThread(volatileData);
        // Start all threads
        for (int i = 0; i < noOfThreads; ++i)
            threadList[i].start();
        // Wait for threads
        for (int i = 0; i < noOfThreads; ++i)
            threadList[i].join();
    }
}
