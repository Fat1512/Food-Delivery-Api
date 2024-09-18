package com.food.phat.dto.menu;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class MenuCategoryRequest {
    private Integer menuCategoryId;
    private String name;
    private List<Integer> menuIds;
    private List<Integer> productIds;
}
