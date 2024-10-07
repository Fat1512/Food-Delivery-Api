package com.food.phat.repository;

import com.food.phat.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRedisRepository extends CrudRepository<Token, String> {
}
