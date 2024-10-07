package com.food.phat.service.Impl;

import com.food.phat.entity.Token;
import com.food.phat.repository.TokenRedisRepository;
import com.food.phat.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRedisRepository tokenRedisRepository;

    @Override
    @Transactional
    public void save(Token token) {
        tokenRedisRepository.save(token);
    }
}
