package com.sas.sample;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Reference: https://www.baeldung.com/java-weakhashmap
 */

class SampleObject {
    private String id;

    public SampleObject(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SampleObject{" +
                "id='" + id + '\'' +
                '}';
    }
}

public class WeakHashmapDemo {
    public static void main(String[] args) throws InterruptedException {
        // Soft Reference
        Integer prime1 = 31;
        SoftReference<Integer> softReference = new SoftReference<>(prime1);
        prime1 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("Soft Reference prime1 after GC: " + prime1);

        // Weak Reference
        Integer prime2 = 29;
        WeakReference<Integer> weakReference = new WeakReference<>(prime2);
        prime2 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("\nWeak Reference prime2 after GC: " + prime2);

        // Weak Reference in WeakHashMap
        SampleObject sampleObject_key1 = new SampleObject("Key 1");
        SampleObject sampleObject_key2 = new SampleObject("Key 2");
        SampleObject sampleObject_val1 = new SampleObject("Value 1");
        SampleObject sampleObject_val2 = new SampleObject("Value 2");

        Map<SampleObject, SampleObject> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(sampleObject_key1, sampleObject_val1);
        weakHashMap.put(sampleObject_key2, sampleObject_val2);

        System.out.println("\nSize of Map before GC: " + weakHashMap.size() + "\n" + weakHashMap);
        sampleObject_key1 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("\nSize of Map after GC: " + weakHashMap.size() + "\n" + weakHashMap);
    }
}
