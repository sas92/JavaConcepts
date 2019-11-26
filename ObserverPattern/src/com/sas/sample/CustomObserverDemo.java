package com.sas.sample;

import java.util.ArrayList;
import java.util.List;

class SasObservable {
    private String message;
    private List<SasObserver> observers = new ArrayList<>();

    public void addObserver(SasObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SasObserver observer) {
        observers.remove(observer);
    }

    public void setMessage(String message) {
        this.message = message;
        observers.forEach(observer -> observer.update(message));
    }
}

interface SasObserver {
    void update(Object o);
}

class CustomObserver implements SasObserver {
    private String message;

    @Override
    public void update(Object o) {
        this.message = (String) o;
        System.out.println("Message: " + message);
    }
}

public class CustomObserverDemo {
    public static void main(String[] args) {
        SasObservable observable = new SasObservable();
        CustomObserver observer = new CustomObserver();
        observable.addObserver(observer);
        observable.setMessage("Hello world");
    }
}
