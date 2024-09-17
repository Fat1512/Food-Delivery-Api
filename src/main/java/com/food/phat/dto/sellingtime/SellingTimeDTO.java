package com.food.phat.dto.sellingtime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SellingTimeDTO {
    private Integer sellingTimeId;
    private Integer dateOfWeek;
    private List<PeriodTimeDTO> periodTime;
    private Boolean isActive;
}
