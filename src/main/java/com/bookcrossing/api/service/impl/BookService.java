package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.entity.Author;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.Genre;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;
import com.bookcrossing.api.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService extends DefaultBaseService<BookDTO, Book, Long> {

    private final BaseService<AuthorDTO, Author, Long> authorBaseService;
    private final BaseService<GenreDTO, Genre, Long> genreBaseService;

    public BookService(
            final BaseMapper<BookDTO, Book> mapper,
            final BaseCrudRepository<Book, Long> repository,
            final BaseService<AuthorDTO, Author, Long> authorBaseService,
            final BaseService<GenreDTO, Genre, Long> genreBaseService) {
        super(mapper, repository);
        this.authorBaseService = authorBaseService;
        this.genreBaseService = genreBaseService;
    }

    @Override
    @Transactional
    public BookDTO persist(BookDTO value) {
        List<AuthorDTO> authorDTOS = value.getAuthors()
                .stream()
                .map(authorBaseService::persist)
                .collect(Collectors.toList());
        value.setAuthors(authorDTOS);
        List<GenreDTO> genreDTOS = value.getGenres()
                .stream()
                .map(genreBaseService::persist)
                .collect(Collectors.toList());
        value.setGenres(genreDTOS);

        return super.persist(value);
    }
}
