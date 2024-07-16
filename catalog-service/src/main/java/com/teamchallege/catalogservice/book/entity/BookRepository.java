package com.teamchallege.catalogservice.book.entity;

import com.teamchallege.common.entities.catalog.Book;
import com.teamchallege.common.entities.catalog.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByCategories(Pageable pageable, Category category);
}
