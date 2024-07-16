package com.teamchallege.catalogservice.category.controller;

import com.teamchallege.catalogservice.category.service.CategoryService;
import com.teamchallege.common.entities.catalog.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/open/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<List<Category>> getBooks() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

}

