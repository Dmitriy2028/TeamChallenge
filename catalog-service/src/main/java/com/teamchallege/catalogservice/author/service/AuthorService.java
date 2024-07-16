package com.teamchallege.catalogservice.author.service;



import com.teamchallege.common.entities.catalog.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(List<String> authors);
}
