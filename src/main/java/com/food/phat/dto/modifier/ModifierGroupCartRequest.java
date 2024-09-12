package com.food.phat.dto.modifier;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ModifierGroupCartRequest {
    private int modifierGroupId;
    private List<Integer> modifiers;
}
