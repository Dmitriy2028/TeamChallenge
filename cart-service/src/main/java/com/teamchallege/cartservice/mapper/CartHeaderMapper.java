package com.teamchallege.cartservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.cartservice.dto.CartHeaderDto;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartHeaderMapper {
    private final CartItemMapper cartItemMapper;

    public CartHeaderDto toDto(CartHeader cartHeader) {
        CartHeaderDto dto = new CartHeaderDto();
        dto.setCartHeaderId(cartHeader.getId());
        if (cartHeader.getUser() != null) {
            dto.setUserId(cartHeader.getUser().getId());
        }
        dto.setTotalPrice(cartHeader.getTotalPrice());
        dto.setCartItems(cartHeader.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
//    public CartHeader toEntity (CartHeaderDto cartHeaderDto){}
}
