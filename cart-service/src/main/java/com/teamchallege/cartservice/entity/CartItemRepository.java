package com.teamchallege.cartservice.entity;

import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.common.entities.cart.CartItem;
import com.teamchallege.common.entities.catalog.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartHeaderAndBook(CartHeader cartHeader, Book book);
}
