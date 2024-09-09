package com.food.phat.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class MenuResponse {
    private Integer menuId;

    private String name;

    private List<MenuCategoryResponse> menuCategory;
}
