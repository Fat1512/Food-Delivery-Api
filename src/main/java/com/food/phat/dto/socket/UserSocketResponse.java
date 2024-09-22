package com.food.phat.dto.socket;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class UserSocketResponse {
    private Integer userId;
    private String name;
    private String avatar;
    private Map<Integer, String> role;
}
