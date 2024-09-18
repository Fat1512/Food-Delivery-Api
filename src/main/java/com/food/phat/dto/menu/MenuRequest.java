package com.food.phat.dto.menu;

import com.food.phat.dto.sellingtime.SellingTimeDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class MenuRequest {
    private Integer menuId;
    private String name;
    private List<SellingTimeDTO> sellingTimes;
    private List<Integer> menuCategoryIds;
}


