package com.sas.dao;

import com.sas.dto.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<Integer, User> userMap = new HashMap<>();
    private static int currentIndex = 0;

    public int saveUser(User user) {
        userMap.put(++currentIndex, user);
        return currentIndex;
    }
}
