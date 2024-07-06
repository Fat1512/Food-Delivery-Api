package com.food.phat.dao;

import com.food.phat.entity.User;

public interface UserDAO {
    User getUserByUsername(String username);
    User getUserById(int userId);
    User save(User user);
}
