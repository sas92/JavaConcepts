package com.sas.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//  ConcurrentHashMap
class ConcurrentHashMapDemo {
    Map<String, Integer> concurrentHashMap;
    Map<String, Integer> hashMap;

    public ConcurrentHashMapDemo() {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 10);
        hashMap.put("Bob", 20);
        hashMap.put("Charlie", 30);
        hashMap.put("David", 40);
        this.hashMap = hashMap;
        this.concurrentHashMap = new ConcurrentHashMap<>(hashMap);
    }
}

public class ConcurrentCollectionsDemo {
    public static void main(String[] args) {
        // ConcurrentHashMap
        ConcurrentHashMapDemo concurrentHashMapDemo = new ConcurrentHashMapDemo();
        Thread t1 = new Thread(() -> {
            //concurrentHashMapDemo.hashMap.forEach((k, v) -> {
            concurrentHashMapDemo.concurrentHashMap.forEach((k, v) -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(k + "->" + v);
            });
        }, "T1");
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //concurrentHashMapDemo.hashMap.remove("Bob");
            concurrentHashMapDemo.concurrentHashMap.remove("Bob");
        }, "T1");
        t1.start();
        t2.start();
    }
}
