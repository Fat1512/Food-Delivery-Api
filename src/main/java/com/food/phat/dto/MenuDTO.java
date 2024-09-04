package com.food.phat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Data
@Setter
@Getter
public class MenuDTO {
    private Integer menuId;

    private String name;

    private List<MenuCategoryDTO> menuCategory;
}
