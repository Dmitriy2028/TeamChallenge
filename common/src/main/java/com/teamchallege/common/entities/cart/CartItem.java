package com.teamchallege.common.entities.cart;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teamchallege.common.entities.catalog.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "cart_header_id")
    @ToString.Exclude
    private CartHeader cartHeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private Double price = (double) 0;

    private Long quantity = 0L;
}
