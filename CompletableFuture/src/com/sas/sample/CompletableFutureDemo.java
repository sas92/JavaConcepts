package com.sas.sample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Reference: https://blog.knoldus.com/future-vs-completablefuture-1/
 */

public class CompletableFutureDemo {
    public static void main(String[] args) {
        Map<String, String> senderReceiverMap = new HashMap<>();
        senderReceiverMap.put("Alice", "Bob");
        senderReceiverMap.put("Bob", "Charlie");
        senderReceiverMap.put("Charlie", "Daniel");
        senderReceiverMap.put("Daniel", "Alice");

        List<CompletableFuture> futureList = new ArrayList<>();

        senderReceiverMap.forEach((sender, receiver) -> {
            // This method is used when you want to return some value from the background task running asynchronously.
            // It takes a Supplier and returns CompletableFuture.
            CompletableFuture future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);     // Intentional delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return sender + " sending mail to " + receiver;
            })
                    // It takes a Function as an argument.
                    // Function is a functional interface which represents a function that it takes argument of type T and returns argument of type R.
                    // We can even apply a sequence of transformations using thenApply() where the result of 1st thenApply() is passed to the 2nd thenApply() and so on.
                    .thenApply(acknowledgement -> {
                        return acknowledgement + " at ";
                    }).thenApply(acknowledgement -> {
                        return acknowledgement + LocalDateTime.now();
                    })
                    // It takes a Consumer and returns CompletableFuture.
                    // It has access to the result of the CompletableFuture on which it is attached.
                    .thenAccept(message -> System.out.println(message))
                    // It also takes a Consumer and returns CompletableFuture.
                    // If you neither need the value of the computation nor want to return some value at the end of the chain,
                    // then you can pass a Runnable lambda to the thenRun() method.
                    // Hence, in this case we do not have access to future’s result.
                    .thenRun(() -> System.out.println("The transaction has been logged at " + LocalDateTime.now()));

            futureList.add(future);
        });

        futureList.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
