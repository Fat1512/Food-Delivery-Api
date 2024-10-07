package com.food.phat.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@RedisHash("Token")
@Data
@Builder
public class Token implements Serializable {
    @Id
    private String uuid;
    private Integer userKey;
    @TimeToLive
    private Long timeToLive;
}
