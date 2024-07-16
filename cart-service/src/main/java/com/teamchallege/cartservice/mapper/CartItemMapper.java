package com.teamchallege.cartservice.mapper;


import com.teamchallege.common.entities.cart.CartItem;
import org.springframework.stereotype.Service;
import com.teamchallege.cartservice.dto.CartItemDto;

@Service
public class CartItemMapper {
    public CartItemDto toDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setCartItemId(cartItem.getId());
        dto.setBookId(cartItem.getBook().getId());
        dto.setPrice(cartItem.getPrice());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

//    public CartItem toEntity (CartItemDto cartItemDto){}
}
