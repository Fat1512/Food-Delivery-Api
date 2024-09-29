package com.food.phat.service;

public interface ProductDataSyncService {
    void create(Integer productId);
    void update(Integer productId);
    void delete(Integer productId);
}
