package com.food.phat.dto;


import lombok.Data;

import java.util.List;

@Data
public class ModifierGroupDTO {
    private int modifierGroupId;
    private String title;
    List<ModifierDTO> modifiers;

    public void addModifier(ModifierDTO modifier) {
        this.modifiers.add(modifier);
    }

    public void addModifier(List<ModifierDTO> modifiers) {
        this.modifiers.addAll(modifiers);
    }
}
