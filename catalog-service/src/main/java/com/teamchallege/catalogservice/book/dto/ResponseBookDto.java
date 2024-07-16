package com.teamchallege.catalogservice.book.dto;

import com.teamchallege.catalogservice.image.dto.ResponseImageDto;
import com.teamchallege.common.entities.catalog.Category;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class ResponseBookDto {
    private Long id;
    private String title;
    private String description;
    private int year;
    private double price;
    private int totalQuantity;
    private boolean isExpected;
    private String language;
    private List<String> authors;
    private List<Category> categories;
    private ResponseImageDto image;
}
