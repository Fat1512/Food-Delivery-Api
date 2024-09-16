package com.food.phat.mapstruct;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.entity.OrderCancel;
import com.food.phat.repository.OrderRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
public interface OrderCancelMapper {
    OrderCancel toEntity(OrderCancelPost orderCancelPost);
}

@Mapper
abstract class OrderCancelDecorator implements OrderCancelMapper {
    @Qualifier("delegate")
    @Autowired
    private OrderCancelMapper delegate;

    @Override
    public OrderCancel toEntity(OrderCancelPost orderCancelPost) {
        return null;
    }
}