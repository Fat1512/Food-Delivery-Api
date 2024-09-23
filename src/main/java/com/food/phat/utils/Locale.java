package com.food.phat.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Locale {
    VIETNAM("vn"),
    US("us"),
    ;

    private final String code;
}
