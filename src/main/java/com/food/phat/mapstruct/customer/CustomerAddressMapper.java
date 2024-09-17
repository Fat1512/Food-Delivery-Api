package com.food.phat.mapstruct.customer;

import com.food.phat.dto.customer.CustomerAddressDTO;
import com.food.phat.entity.CustomerAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper {
    CustomerAddressDTO toDto(CustomerAddress customerAddress);
}
