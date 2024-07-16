package com.teamchallege.common.entities.catalog;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @Column(length = 2000)
    private String description;

    private int year;

    private String language;

    @OneToOne
    private Image images;


    private double price;

    @Column(name = "total_quantity")
    private int totalQuantity;

    private boolean isExpected;

    private Integer discount;

    @CreatedDate
    private LocalDateTime createdAt;
}
