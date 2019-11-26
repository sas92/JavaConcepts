package com.sas.runnables;

import com.sas.dao.UserDao;
import com.sas.dto.User;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class UserProcessor implements Callable<Integer> {
    private String userRecord;
    private UserDao userDao;

    public UserProcessor(String userRecord, UserDao userDao) {
        this.userRecord = userRecord;
        this.userDao = userDao;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " : " + userRecord);
        StringTokenizer tokenizer = new StringTokenizer(userRecord, ",");
        User user = null;
        int userId = 0;
        while (tokenizer.hasMoreElements()) {
            user = new User();
            user.setEmail(tokenizer.nextToken());
            user.setName(tokenizer.nextToken());
            user.setId(Integer.valueOf(tokenizer.nextToken()));
            userId = userDao.saveUser(user);
        }
        return userId;
    }
}
