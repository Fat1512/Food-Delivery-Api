package com.food.phat.mapstruct;

import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.dto.order.OrderItemResponse;
import com.food.phat.entity.OrderItem;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderItemDecorator.class)
public interface OrderItemMapper {
    @Mapping(target="price", source="product.price")
    @Mapping(target="thumbnail", source="product.thumbnail")
    @Mapping(target="name", source="product.name")
    @Mapping(target="productId", source="product.productId")
    @Mapping(target="restaurantId", source="product.restaurant.restaurantId")
    @Mapping(target="status", source="product.status")
    @Mapping(target = "modifierGroups", ignore = true)
    OrderItemResponse toDto(OrderItem orderItem);
}

@Mapper
abstract class OrderItemDecorator implements OrderItemMapper {

    @Qualifier("delegate")
    @Autowired
    private OrderItemMapper delegate;

    @Autowired
    private OrderModifierMapper orderModifierMapper;

    @Override
    public OrderItemResponse toDto(OrderItem orderItem) {
        OrderItemResponse orderItemResponse = delegate.toDto(orderItem);
        List<ModifierGroupResponse> modifierGroupResponses = orderModifierMapper.toDto(orderItem.getModifiers());
        orderItemResponse.setModifierGroups(modifierGroupResponses);
        return orderItemResponse;
    }
}