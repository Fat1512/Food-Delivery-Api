package com.food.phat.dto;

import lombok.Builder;

@Builder
public class ExceptionResponse {
    private String error;
    private int status;
    private String message;
    private Long timestamp;
}
