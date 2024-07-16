package com.teamchallege.cartservice.controller;

import com.teamchallege.cartservice.dto.AddUserToCartDto;
import com.teamchallege.cartservice.mapper.CartHeaderMapper;
import com.teamchallege.cartservice.service.CartService;
import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.common.entities.catalog.Book;
import com.teamchallege.common.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("internal/cart")
@RequiredArgsConstructor
public class InternalCartController {

    private final CartService cartService;

    @PostMapping("/addUserToCart")
    public ResponseEntity<CartHeader> addUserToCart(@RequestBody AddUserToCartDto addUserToCartDto){
        return ResponseEntity.ok(cartService.addUserToCart(addUserToCartDto.getUser(), addUserToCartDto.getCartHeaderId()));
    }

    @PostMapping("/createCart")
    public ResponseEntity<CartHeader> createCart(@RequestBody User user){
        return ResponseEntity.ok(cartService.createCart(user));
    }

}
