package com.teamchallege.cartservice.dto;

import lombok.Data;

@Data
public class CartItemDto {
    Long cartItemId;
    Long bookId;
    Long quantity;
    Double price;
}
