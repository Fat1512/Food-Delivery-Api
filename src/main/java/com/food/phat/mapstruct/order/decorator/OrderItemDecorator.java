package com.food.phat.mapstruct.order.decorator;

import com.food.phat.dto.modifier.ModifierGroupResponse;
import com.food.phat.dto.order.OrderItemPost;
import com.food.phat.dto.order.OrderItemResponse;
import com.food.phat.entity.OrderItem;
import com.food.phat.entity.OrderModifier;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.order.OrderItemMapper;
import com.food.phat.mapstruct.order.OrderModifierMapper;
import com.food.phat.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
public abstract class OrderItemDecorator implements OrderItemMapper {

    @Qualifier("delegate")
    @Autowired
    private OrderItemMapper delegate;

    @Autowired
    private OrderModifierMapper orderModifierMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderItemResponse toDto(OrderItem orderItem) {
        OrderItemResponse orderItemResponse = delegate.toDto(orderItem);
        orderItemResponse.setModifierGroups(orderModifierMapper.toDto(orderItem.getModifiers()));
        return orderItemResponse;
    }

    @Override
    public OrderItem toEntity(OrderItemPost orderItemPost) {
        OrderItem orderItem = delegate.toEntity(orderItemPost);

        Product product =  productRepository.findById(orderItemPost.getProductId()).get();
        List<OrderModifier> orderModifiers = orderModifierMapper.toEntity(orderItem, orderItemPost.getModifierGroups());
        orderItem.setProduct(product);
        orderItem.setModifiers(orderModifiers);

        return orderItem;
    }
}
