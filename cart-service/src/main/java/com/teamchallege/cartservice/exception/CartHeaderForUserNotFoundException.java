package com.teamchallege.cartservice.exception;


import com.teamchallege.common.entities.user.User;

public class CartHeaderForUserNotFoundException extends RuntimeException {
    public CartHeaderForUserNotFoundException(User user) {
        super("Cart for user " + user.toString() + " not found");
    }
}
