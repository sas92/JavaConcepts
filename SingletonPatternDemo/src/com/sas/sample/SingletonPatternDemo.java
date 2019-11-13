package com.sas.sample;

/**
 * Reference: https://www.oodesign.com/singleton-pattern.html#early-singleton
 * https://www.oodesign.com/singleton-pattern.html#lazy-singleton
 */

class EarlySingleton {
    private static EarlySingleton instance = new EarlySingleton();

    private EarlySingleton() {
        System.out.println("Initializing EarlySingleton instance");
    }

    public static EarlySingleton getInstance() {
        return instance;
    }

    public void method() {
        System.out.println("Inside EarlySingleton::method");
    }
}

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Initializing LazySingleton instance");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public void method() {
        System.out.println("Inside LazySingleton::method");
    }
}

public class SingletonPatternDemo {
    public static void main(String[] args) {
        EarlySingleton instance1;
        System.out.println("Requesting for EarlySingleton instance..");
        instance1 = EarlySingleton.getInstance();
        instance1.method();

        LazySingleton instance2;
        System.out.println("\nRequesting for LazySingleton instance..");
        instance2 = LazySingleton.getInstance();
        instance2.method();
    }
}
