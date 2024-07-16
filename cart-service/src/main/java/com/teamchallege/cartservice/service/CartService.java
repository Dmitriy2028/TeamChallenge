package com.teamchallege.cartservice.service;

import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.cartservice.dto.CartHeaderDto;
import com.teamchallege.common.entities.user.User;


import java.util.List;

public interface CartService {
    List<CartHeaderDto> getAllCartHeaders();

    CartHeaderDto getCartHeaderById(Long id);

    CartHeader getCartHeaderEntityById(Long id);

    Long saveCartHeader(CartHeader cartHeader);

    void deleteCartHeader(Long id);


    CartHeader createCart(User user);

    Long createCart();

    CartHeader addUserToCart(User user, Long cartHeaderId);

    CartHeaderDto getCartByUser(String email);

    CartHeaderDto addBook(Long bookId, Long quantity);

    CartHeaderDto addBook(Long bookId, Long quantity, Long cartHeaderId);

    CartHeaderDto removeBook(Long bookId, Long quantity);

    CartHeaderDto removeBook(Long bookId, Long quantity, Long cartHeaderId);

    CartHeaderDto removeAllBooks();

    CartHeaderDto removeAllBooks(Long cartHeaderId);

}
