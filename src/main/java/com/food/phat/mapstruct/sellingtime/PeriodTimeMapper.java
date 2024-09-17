package com.food.phat.mapstruct.sellingtime;

import com.food.phat.dto.sellingtime.PeriodTimeDTO;
import com.food.phat.entity.PeriodTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeriodTimeMapper {
    PeriodTimeDTO toDto(PeriodTime periodTime);
    PeriodTime toEntity(PeriodTimeDTO periodTimeDTO);
}
