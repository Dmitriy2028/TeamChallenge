package com.teamchallege.catalogservice.book.controller;

import com.teamchallege.catalogservice.book.dto.ListResponseBookDto;
import com.teamchallege.catalogservice.book.dto.ResponseBookDto;
import com.teamchallege.catalogservice.book.mapper.BookMapper;
import com.teamchallege.catalogservice.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/open/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBooks(@PathVariable Long id) {
        return ResponseEntity.ok(bookMapper.mapBookToResponseBookDto(bookService.getBookById((id))));
    }

    @PostMapping("/_list")
    public ResponseEntity<Page<ListResponseBookDto>> getBooks(Pageable pageable, @RequestParam(required = false) Long category) {
        return ResponseEntity.ok(bookService.getBooks(pageable, category));
    }

}
