package com.food.phat.service;

import com.food.phat.entity.Token;

public interface TokenService {
    void save(Token token);
    void delete(String uuid);
    Token get(String uuid);
    void invalidateAllUserToken(Integer userId);
}
