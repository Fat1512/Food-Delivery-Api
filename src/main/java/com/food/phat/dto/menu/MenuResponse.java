package com.food.phat.dto.menu;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class MenuResponse {
    private Integer menuId;

    private List<MenuCategoryResponse> menuCategory;
}
