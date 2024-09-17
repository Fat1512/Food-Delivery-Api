package com.food.phat.mapstruct.sellingtime;

import com.food.phat.dto.sellingtime.SellingTimeDTO;
import com.food.phat.entity.SellingTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PeriodTimeMapper.class)
public interface SellingTimeMapper {
    SellingTimeDTO toDto(SellingTime sellingTime);
    SellingTime toEntity(SellingTimeDTO sellingTimeDTO);
}
