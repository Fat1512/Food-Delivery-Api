package com.food.phat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class MessageResponse implements Serializable {
    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 7702134516418120340L;

    @JsonProperty("message")
    private String message;

    @JsonProperty
    private Object data;

    @JsonIgnore
    private HttpStatus status;
}
