package com.food.phat.utils;

public enum ApiResponseMessage {
    SUCCESSFULLY_CREATED("Successfully created"),
    SUCCESSFULLY_UPDATED("Successfully updated"),
    SUCCESSFULLY_DELETED("Successfully deleted"),
    SUCCESSFULLY_RETRIEVED("Successfully retrieved"),

    SUCCESSFULLY_LOGIN("Successfully login"),
    SUCCESSFULLY_REGISTER("Successfully register"),
    SUCCESSFULLY_LOGOUT("Successfully logout");

    final String message;
    ApiResponseMessage(String message) {
        this.message = message;
    }
}
