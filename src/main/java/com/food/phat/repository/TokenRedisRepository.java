package com.food.phat.repository;

import com.food.phat.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRedisRepository extends CrudRepository<Token, String> {
    List<Token> findByUserKey(Integer userKey);
    void deleteAll(List<String> uuid);
}
