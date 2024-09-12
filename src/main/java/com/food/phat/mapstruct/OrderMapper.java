package com.food.phat.mapstruct;


import com.food.phat.dto.order.OrderResponse;
import com.food.phat.entity.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
    OrderResponse toDto(Order order);
}

@Mapper
abstract class OrderMapperDecorator implements OrderMapper {

    @Qualifier("delegate")
    @Autowired
    private OrderMapper delegate;

    @Override
    public OrderResponse toDto(Order order) {
        return null;
    }
}