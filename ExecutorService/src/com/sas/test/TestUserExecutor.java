package com.sas.test;

import com.sas.dao.UserDao;
import com.sas.runnables.UserProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestUserExecutor {
    public static List<String> getUsersFromFile(String fileName) {
        List<String> userList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                userList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<String> userList = getUsersFromFile("D:\\SasWorkspace\\Git Repo\\JavaConcepts\\ExecutorService\\src\\com\\sas\\new_users.txt");
        UserDao userDao = new UserDao();
        for (String user : userList) {
            // Future is blocking
            /*
            Future<Integer> future = executorService.submit(new UserProcessor(user, userDao));
            try {
                System.out.println("Result: " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            */
            executorService.submit(new UserProcessor(user, userDao));
        }
        executorService.shutdown();
        System.out.println("Main execution over");
    }
}
