package com.food.phat.mapstruct.order.decorator;

import com.food.phat.dto.order.OrderCancelPost;
import com.food.phat.entity.OrderCancel;
import com.food.phat.mapstruct.order.OrderCancelMapper;
import com.food.phat.repository.OrderCancelRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public abstract class OrderCancelDecorator implements OrderCancelMapper {
    @Qualifier("delegate")
    @Autowired
    private OrderCancelMapper delegate;

    @Autowired
    private OrderCancelRepository orderCancelRepository;
    @Override
    public OrderCancel toEntity(OrderCancelPost orderCancelPost) {
        orderCancelRepository.findById(orderCancelPost.getOrderId());
        return null;
    }
}
