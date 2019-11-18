package com.sas.sample;

/**
 * Class and Object level lock
 */

class DemoClass {
    synchronized void m1() throws InterruptedException {
        System.out.println("m1 started by " + Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("m1 ended by " + Thread.currentThread().getName());
    }

    synchronized void m2() throws InterruptedException {
        System.out.println("m2 started by " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("m2 ended by " + Thread.currentThread().getName());
    }

    synchronized static void m3() throws InterruptedException {
        System.out.println("m3 started by " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("m3 ended by " + Thread.currentThread().getName());
    }

    synchronized static void m4() throws InterruptedException {
        System.out.println("m4 started by " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("m4 ended by " + Thread.currentThread().getName());
    }

    void m5() throws InterruptedException {
        System.out.println("m5 started by " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("m5 ended by " + Thread.currentThread().getName());
    }

    void m6() throws InterruptedException {
        System.out.println("m6 started by " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("m6 ended by " + Thread.currentThread().getName());
    }
}

public class ThreadDemo {
    private static DemoClass demoClass = new DemoClass();

    public static void main(String[] args) {
        Runnable r1 = () -> {
            try {
                demoClass.m3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                demoClass.m2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(r1, "T1");
        Thread t2 = new Thread(r2, "T2");
        t1.start();
        t2.start();
    }
}


