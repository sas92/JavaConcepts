package com.sas.sample;

import java.util.concurrent.CountDownLatch;

/**
 * Reference: https://www.linkedin.com/learning/learning-java-threads/synchronizers?u=2113185
 * https://www.geeksforgeeks.org/countdownlatch-in-java/
 */

class Worker implements Runnable {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch) {
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " started, Countdown: " + latch.getCount());
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished, Countdown: " + latch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class LatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println(Thread.currentThread().getName() + " started, Countdown: " + countDownLatch.getCount());
        Thread w1 = new Thread(new Worker(1000, countDownLatch),
                "Worker-1");
        Thread w2 = new Thread(new Worker(2000, countDownLatch),
                "Worker-2");
        Thread w3 = new Thread(new Worker(3000, countDownLatch),
                "Worker-3");
        Thread w4 = new Thread(new Worker(4000, countDownLatch),
                "Worker-4");
        w1.start();
        w2.start();
        w3.start();
        w4.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished, Countdown: " + countDownLatch.getCount());
    }
}
