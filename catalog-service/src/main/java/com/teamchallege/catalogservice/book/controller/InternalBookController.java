package com.teamchallege.catalogservice.book.controller;


import com.teamchallege.catalogservice.book.service.BookService;
import com.teamchallege.common.entities.catalog.Book;
import com.teamchallege.common.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("internal/catalog/book")
@RequiredArgsConstructor
public class InternalBookController {

    private final BookService bookService;
    @PostMapping("/getBookById")
    public ResponseEntity<Book> getBookById(@RequestBody Long bookId){
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

}
