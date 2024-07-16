package com.teamchallege.catalogservice.book.dto;

import com.teamchallege.catalogservice.image.dto.ResponseImageDto;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class ListResponseBookDto {
    private Long id;
    private String title;
    private int year;
    private double price;
    private int totalQuantity;
    private String language;
    private boolean isExpected;
    private List<String> authors;
    private ResponseImageDto image;
}
