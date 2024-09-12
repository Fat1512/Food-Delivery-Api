package com.food.phat.mapstruct;

import com.food.phat.dto.modifier.ModifierGroupDTO;
import com.food.phat.dto.cart.CartItemResponse;
import com.food.phat.entity.CartItem;
import com.food.phat.entity.CartModifier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = ModifierMapper.class, injectionStrategy = InjectionStrategy.FIELD)
@DecoratedWith(CartItemDecorator.class)
public interface CartItemMapper {
    @Mapping(target = "productId", source="product.productId")
    @Mapping(target = "restaurantId", source="product.restaurant.restaurantId")
    @Mapping(target = "name", source="product.name")
    @Mapping(target = "status", source="product.status")
    @Mapping(target = "thumbnail", source="product.thumbnail")
    @Mapping(target = "modifierGroups", ignore = true)
    CartItemResponse toDto(CartItem cartItem);
}

@Mapper
abstract class CartItemDecorator implements CartItemMapper {
    @Qualifier("delegate")
    @Autowired
    private CartItemMapper delegate;


    @Override
    public CartItemResponse toDto(CartItem cartItem) {
        List<ModifierGroupDTO> modifierGroups = this.cartModifierToModifierGroupDto(cartItem.getModifiers());
        CartItemResponse cartItemResponse = delegate.toDto(cartItem);
        cartItemResponse.setModifierGroups(modifierGroups);
        return cartItemResponse;
    }

    private final ModifierMapper modifierMapper = Mappers.getMapper(ModifierMapper.class);

    private List<ModifierGroupDTO> cartModifierToModifierGroupDto(List<CartModifier> cartModifiers) {
        Map<Integer, ModifierGroupDTO> modifierGroupMp = new HashMap<>();

        cartModifiers.forEach(cartModifier -> {
            Integer modifierGroupId = cartModifier.getModifierGroup().getModifierGroupId();
            modifierGroupMp.putIfAbsent(modifierGroupId, new ModifierGroupDTO());
            modifierGroupMp.get(modifierGroupId).addModifier(modifierMapper.toDto(cartModifier.getModifier()));
        });

        return new ArrayList<>(modifierGroupMp.values());
    }
}
















