package com.sas.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * Reference: https://www.baeldung.com/java-semaphore
 * <p>
 * tryAcquire() – return true if a permit is available immediately and acquire it otherwise return false, but acquire() acquires a permit and blocking until one is available
 * release() – release a permit
 * availablePermits() – return number of current permits available
 *
 * Semaphores – Restrict the number of threads that can access a resource. Example, limit max 10 connections to access a file simultaneously.
 * Mutex – Only one thread to access a resource at once. Example, when a client is accessing a file, no one else should have access the same file at the same time.
 */

class LoginSemaphore {
    private Semaphore semaphore;

    public LoginSemaphore(int permits) {
        semaphore = new Semaphore(permits);
    }

    public boolean tryLogin() {
        return semaphore.tryAcquire();
    }

    public void logout() {
        semaphore.release();
    }

    public int availablePermits() {
        return semaphore.availablePermits();
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        final int PERMITS = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(PERMITS);
        LoginSemaphore loginSemaphore = new LoginSemaphore(PERMITS);
        System.out.println("Available permits before Login: " + loginSemaphore.availablePermits());
        IntStream.range(0, PERMITS)
                .forEach(user -> executorService.execute(loginSemaphore::tryLogin));
        Thread.sleep(500);     // Intentional delay
        System.out.println("Available permits after 10 Login: " + loginSemaphore.availablePermits());
        loginSemaphore.logout();
        System.out.println("Available permits after a Logout: " + loginSemaphore.availablePermits());
        executorService.shutdown();
    }
}
