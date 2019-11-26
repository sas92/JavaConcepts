package com.sas.test;

import com.sas.custom.CustomThreadFactory;
import com.sas.runnables.LoggingProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TestThreadFactory {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3, new CustomThreadFactory());
        IntStream.range(0, 6).forEach(i ->
                executorService.submit(new LoggingProcessor()));
    }
}
