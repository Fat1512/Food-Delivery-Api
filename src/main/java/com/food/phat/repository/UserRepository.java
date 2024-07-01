package com.food.phat.repository;

import com.food.phat.entity.User;

public interface UserRepository  {
    User getUserByUsername(String username);
    User getUserById(int userId);
    User save(User user);
}
