package com.teamchallege.catalogservice.book.service;

import com.teamchallege.catalogservice.book.dto.CreateBookDto;
import com.teamchallege.catalogservice.book.dto.ListResponseBookDto;
import com.teamchallege.catalogservice.book.dto.ResponseBookDto;
import com.teamchallege.common.entities.catalog.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BookService {
    Book createBook(CreateBookDto createBookDto);

    void saveImages(Long id, MultipartFile images);

    Book getBookById(Long id);

    Page<ListResponseBookDto> getBooks(Pageable pageable, Long category);
}
