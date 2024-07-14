package com.food.phat.repository;

import java.util.List;

public interface CustomCartRepo {
    List getCartItems(String username);
}
