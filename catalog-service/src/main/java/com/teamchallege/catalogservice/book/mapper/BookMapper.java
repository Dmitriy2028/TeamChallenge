package com.teamchallege.catalogservice.book.mapper;

import com.teamchallege.catalogservice.book.dto.ListResponseBookDto;
import com.teamchallege.catalogservice.book.dto.ResponseBookDto;
import com.teamchallege.catalogservice.image.service.ImageService;
import com.teamchallege.common.entities.catalog.Author;
import com.teamchallege.common.entities.catalog.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMapper {
    private final ImageService imageService;

    public ListResponseBookDto mapBookToListResponseBookDto(Book book) {
        return ListResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year(book.getYear())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .language(book.getLanguage())
                .authors(book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .toList())
                .image(imageService.getImageDto(book.getImages()))
                .build();
    }

    public ResponseBookDto mapBookToResponseBookDto(Book book) {
        return ResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categories(book.getCategories())
                .authors(book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .toList())
                .description(book.getDescription())
                .year(book.getYear())
                .price(book.getPrice())
                .language(book.getLanguage())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .image(imageService.getImageDto(book.getImages()))
                .build();
    }
}
