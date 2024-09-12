package com.food.phat.dto.modifier;

import lombok.Data;

@Data
public class ModifierDTO {
    private int modifierId;
    private String title;
    private float price;
    private boolean status;
}
