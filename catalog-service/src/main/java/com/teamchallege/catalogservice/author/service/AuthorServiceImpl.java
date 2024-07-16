package com.teamchallege.catalogservice.author.service;

import com.teamchallege.catalogservice.author.entity.AuthorRepository;
import com.teamchallege.common.entities.catalog.Author;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors(List<String> authors) {
        List<Author> result = new ArrayList<>();

        for (String authorName : authors) {
            Author author = authorRepository.findByName(authorName).orElse(createAndGetAuthor(authorName));
            result.add(author);
        }
        return result;
    }

    private Author createAndGetAuthor(String name) {
        Author author = new Author();
        author.setName(WordUtils.capitalizeFully(name.toLowerCase()));
        authorRepository.save(author);
        return author;
    }

}
