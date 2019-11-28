package com.sas.sample;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * Reference: https://www.baeldung.com/java-concurrent-locks
 */

class ReaderWriter {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Map<Integer, String> data = new TreeMap<>();

    void insertData(Integer id, String task) {
        try {
            writeLock.lock();
            System.out.println("\n" + Thread.currentThread().getName() + " locked");
            Thread.sleep(1000);
            data.put(id, task);
            System.out.println("Data inserted: " + id + "-> " + task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " unlocked");
        }
    }

    void readData(Integer id) {
        try {
            readLock.lock();
            System.out.println("\n" + Thread.currentThread().getName() + " locked");
            Thread.sleep(1000);
            System.out.println("Data fetched: " + id + "-> " + data.get(id));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " unlocked");
        }
    }
}

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReaderWriter readerWriter = new ReaderWriter();
        Thread writer1 = new Thread(() -> {
            IntStream.range(1, 5).forEach(i -> {
                readerWriter.insertData(i, "Para " + i);
            });
        }, "Writer 1");
        Thread writer2 = new Thread(() -> {
            IntStream.range(6, 10).forEach(i -> {
                readerWriter.insertData(i, "Para " + i);
            });
        }, "Writer 2");
        Thread reader1 = new Thread(() -> {
            IntStream.range(1, 5).forEach(i -> {
                readerWriter.readData(i);
            });
        }, "Reader 1");
        Thread reader2 = new Thread(() -> {
            IntStream.range(6, 10).forEach(i -> {
                readerWriter.readData(i);
            });
        }, "Reader 2");

        writer1.start();
        writer2.start();
        Thread.sleep(2000);     // Intentional delay
        reader1.start();
        reader2.start();

        writer1.join();
        writer2.join();
        reader1.join();
        reader2.join();
    }
}
