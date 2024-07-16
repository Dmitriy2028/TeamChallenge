package com.teamchallege.cartservice.controller;

import com.teamchallege.cartservice.jwt.JwtUtils;
import com.teamchallege.cartservice.service.CartServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.teamchallege.cartservice.dto.CartHeaderDto;
import com.teamchallege.cartservice.dto.ChangeBookQuantityInCartDto;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;

@RestController
@RequestMapping("api/open/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;
    private final JwtUtils jwtUtils;

    @GetMapping("/{id}")
    public ResponseEntity<CartHeaderDto> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartHeaderById(id));
    }

    @PostMapping("/create-cart")
    public ResponseEntity<Long> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }

    @PostMapping("/add-book")
    public ResponseEntity<CartHeaderDto> addBookToCart(@RequestBody ChangeBookQuantityInCartDto request, @RequestHeader(value= "Authorization", required = false) String token) {
        //SecurityContextHolder.getContext().setAuthentication(token);
        if (request.getCartHeaderId() != null) {
            //SecurityContextHolder.getContext().setAuthentication(token);
            return ResponseEntity.ok(cartService.addBook(request.getBookId(), request.getQuantity(), request.getCartHeaderId()));
        } else {
            SecurityContextHolder.getContext().setAuthentication(jwtUtils.toAuthentication(token));
            return ResponseEntity.ok(cartService.addBook(request.getBookId(), request.getQuantity()));
        }
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<CartHeaderDto> removeBookFromCart(@RequestBody ChangeBookQuantityInCartDto request, @RequestHeader(value= "Authorization", required = false) String token) {
        if (request.getCartHeaderId() != null) {
            return ResponseEntity.ok(cartService.removeBook(request.getBookId(), request.getQuantity(), request.getCartHeaderId()));
        } else {
            SecurityContextHolder.getContext().setAuthentication(jwtUtils.toAuthentication(token));
            return ResponseEntity.ok(cartService.removeBook(request.getBookId(), request.getQuantity()));
        }
    }

    @DeleteMapping("/clear-cart")
    public ResponseEntity<CartHeaderDto> removeAllBooksFromCart(@RequestHeader(value= "Authorization", required = false) String token) {
        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(jwtUtils.toAuthentication(token));
        }
        return ResponseEntity.ok(cartService.removeAllBooks());
    }


}
