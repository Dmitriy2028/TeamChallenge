package com.teamchallege.catalogservice.category.service;



import com.teamchallege.common.entities.catalog.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories(List<String> categories);

    List<Category> getCategories();

    void createCategory(String name);

    Category getCategoryById(Long category);
}
