package com.food.phat.dto.menu;

import com.food.phat.dto.sellingtime.SellingTimeDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MenuResponse {
    private Integer menuId;
    private String name;
    private List<MenuCategoryResponse> menuCategories;
    private List<SellingTimeDTO> sellingTimes;
}
