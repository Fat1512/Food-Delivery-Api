package com.food.phat.dto;

import com.food.phat.entity.Modifier;
import lombok.Data;

@Data
public class ModifierOptionDTO {
    private int modifierOptionId;

    private String title;

    private float price;

    private boolean status;

    private Modifier modifier;
}
