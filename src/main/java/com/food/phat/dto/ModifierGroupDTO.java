package com.food.phat.dto;


import lombok.Data;

import java.util.List;

@Data
public class ModifierGroupDTO {
    private int modifierGroupId;
    private String title;
    List<ModifierDTO> modifiers;
}
