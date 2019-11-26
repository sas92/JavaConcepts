package com.sas.test;

import com.sas.runnables.LoggingProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestLogging {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());
        callableList.add(new LoggingProcessor());

        // Invoke All
        try {
            List<Future<Boolean>> futureList = executorService.invokeAll(callableList);
            for (Future future : futureList) {
                System.out.println("Result: " + future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Invoke Any
        try {
            System.out.println(executorService.invokeAny(callableList));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        try {
            System.out.println("Service shutdown? "+ executorService.awaitTermination(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            e.printStackTrace();
        }

    }
}
