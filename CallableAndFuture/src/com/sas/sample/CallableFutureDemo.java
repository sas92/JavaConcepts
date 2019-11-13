package com.sas.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Reference: https://www.baeldung.com/java-runnable-callable
 * https://dzone.com/articles/java-callable-future-understanding
 * https://www.journaldev.com/1090/java-callable-future-example
 */

class SumCalculator implements Callable<Integer> {
    private IntStream numbers;

    SumCalculator(IntStream stream) {
        numbers = stream;
    }

    @Override
    public Integer call() {
        return sum();
    }

    private Integer sum() {
        try {
            Thread.sleep(1000);     // Intentional delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return numbers.sum();
    }
}

public class CallableFutureDemo {
    private static Integer parallelSum(final int start, final int end) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futureList = new ArrayList<>();
        int count = end + 1 - start;
        int mid = count / 2;
        int result = 0;
        try {
            futureList.add(executorService.submit(
                    new SumCalculator(
                            IntStream.range(start, mid)
                    )));
            futureList.add(executorService.submit(
                    new SumCalculator(
                            IntStream.range(mid, end + 1)
                    )));
            for (Future<Integer> future : futureList)
                result += future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return result;
    }

    private static Integer sequentialSum(final int start, final int end) {
        return IntStream.range(start, end + 1).sum();
    }

    public static void main(String[] args) {
        Integer sequentialSum = sequentialSum(1, 100);
        Integer parallelSum = parallelSum(1, 100);
        System.out.println("The sum is: " + sequentialSum +
                "\nResult matches: " + (sequentialSum.equals(parallelSum)));
    }
}
