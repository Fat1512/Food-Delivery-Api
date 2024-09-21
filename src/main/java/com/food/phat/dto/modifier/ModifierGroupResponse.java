package com.food.phat.dto.modifier;


import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class ModifierGroupResponse {
    private int modifierGroupId;
    private String title;
    List<ModifierResponse> modifiers = new ArrayList<>();

    public void addModifier(ModifierResponse modifier) {
        this.addModifier(Collections.singletonList(modifier));
    }
    public void addModifier(List<ModifierResponse> modifiers) {
        this.modifiers.addAll(modifiers);
    }
}
