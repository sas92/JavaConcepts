package com.sas.sample;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Reference: https://www.baeldung.com/java-atomic-variables
 */

public class AtomicDemo {
    public final AtomicInteger counter1 = new AtomicInteger();  // Initialized to 0
    public final AtomicInteger counter2 = new AtomicInteger();

    private void increment() {
        while (true) {
            int existingValue = counter1.get();
            int newValue = existingValue + 1;
            if (counter1.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    private void incrementNonSafe() {
        counter2.set(counter2.get() + 1);
    }

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        Runnable runnable = () -> {
            IntStream.range(0, 100).forEach(i -> {
                atomicDemo.increment();
                atomicDemo.incrementNonSafe();
            });
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("counter1: " + atomicDemo.counter1.get());
        System.out.println("counter2: " + atomicDemo.counter2.get());
    }
}
