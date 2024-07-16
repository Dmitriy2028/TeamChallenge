package com.teamchallege.catalogservice.book.service;

import com.teamchallege.catalogservice.author.service.AuthorService;
import com.teamchallege.catalogservice.book.dto.CreateBookDto;
import com.teamchallege.catalogservice.book.dto.ListResponseBookDto;
import com.teamchallege.catalogservice.book.dto.ResponseBookDto;
import com.teamchallege.catalogservice.book.entity.BookRepository;
import com.teamchallege.catalogservice.book.exception.BookNotFoundException;
import com.teamchallege.catalogservice.book.mapper.BookMapper;
import com.teamchallege.catalogservice.category.service.CategoryService;
import com.teamchallege.catalogservice.image.service.ImageService;
import com.teamchallege.common.entities.catalog.Author;
import com.teamchallege.common.entities.catalog.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final ImageService imageService;
    private final BookMapper bookMapper;

    @Override
    public Book createBook(CreateBookDto createBookDto) {
        Book book = new Book();
        book.setTitle(createBookDto.getTitle());
        book.setCategories(categoryService.getCategories(createBookDto.getCategories()));
        book.setAuthors(authorService.getAuthors(createBookDto.getAuthors()));
        book.setDescription(createBookDto.getDescription());
        book.setYear(createBookDto.getYear());
        book.setPrice(createBookDto.getPrice());
        book.setTotalQuantity(createBookDto.getTotalQuantity());
        book.setExpected(createBookDto.isExpected());
        book.setLanguage(createBookDto.getLanguage());
        book.setDiscount(createBookDto.getDiscount());
        book.setCreatedAt(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public void saveImages(Long id, MultipartFile image) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setImages(imageService.saveImage(image));
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    @Transactional
    public Page<ListResponseBookDto> getBooks(Pageable pageable, Long category) {
        if (category != null) {
            return bookRepository.findAllByCategories(pageable, categoryService.getCategoryById(category))
                    .map(bookMapper::mapBookToListResponseBookDto);
        }
        return bookRepository.findAll(pageable)
                .map(bookMapper::mapBookToListResponseBookDto);
    }
}
