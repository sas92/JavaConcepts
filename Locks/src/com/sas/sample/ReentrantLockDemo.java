package com.sas.sample;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Reference: https://examples.javacodegeeks.com/core-java/util/concurrent/locks-concurrent/reentrantlock/java-reentrantlock-example/
 */

class ReentrantDemoClass {
    ReentrantLock reentrantLock = new ReentrantLock(true);
    public static int counter = 0;

    void m1() {
        try {
            reentrantLock.lock();
            System.out.println("m1 started by " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Counter: " + (++counter));
            System.out.println("m1 ended by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    void m2() {
        try {
            reentrantLock.lock();
            System.out.println("m2 started by " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Counter: " + (++counter));
            System.out.println("m2 ended by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantDemoClass demoClass = new ReentrantDemoClass();
        Runnable r1 = () -> {
            while (ReentrantDemoClass.counter < 5)
                demoClass.m1();
        };
        Runnable r2 = () -> {
            while (ReentrantDemoClass.counter < 5)
                demoClass.m1();
        };

        Thread t1 = new Thread(r1, "T1");
        Thread t2 = new Thread(r2, "T2");
        t1.start();
        t2.start();
    }
}
