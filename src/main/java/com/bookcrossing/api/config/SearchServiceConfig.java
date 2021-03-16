package com.bookcrossing.api.config;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.entity.Author;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.Genre;
import com.bookcrossing.api.domain.mapper.AuthorMapper;
import com.bookcrossing.api.domain.mapper.BookMapper;
import com.bookcrossing.api.domain.mapper.GenreMapper;
import com.bookcrossing.api.domain.repository.AuthorRepository;
import com.bookcrossing.api.domain.repository.BookRepository;
import com.bookcrossing.api.domain.repository.GenreRepository;
import com.bookcrossing.api.service.search.BaseNamedSearchService;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.search.factory.PredicateFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchServiceConfig {

    //TODO think about factory or create separate services
    private final PredicateFactory<BaseNamedSearch, Genre> genrePredicateFactory;
    private final PredicateFactory<BaseNamedSearch, Author> authorPredicateFactory;
    private final PredicateFactory<BookSearch, Book> bookPredicateFactory;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    private final BookMapper bookMapper;

    @Autowired
    public SearchServiceConfig(
            final PredicateFactory<BaseNamedSearch, Genre> genrePredicateFactory,
            final PredicateFactory<BaseNamedSearch, Author> authorPredicateFactory,
            final PredicateFactory<BookSearch, Book> bookPredicateFactory,
            final GenreRepository genreRepository,
            final AuthorRepository authorRepository,
            final BookRepository bookRepository,
            final AuthorMapper authorMapper,
            final GenreMapper genreMapper,
            final BookMapper bookMapper) {
        this.genrePredicateFactory = genrePredicateFactory;
        this.authorPredicateFactory = authorPredicateFactory;
        this.bookPredicateFactory = bookPredicateFactory;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
        this.bookMapper = bookMapper;
    }

    @Bean
    public SearchService<BaseNamedSearch, List<AuthorDTO>> authorNameSearchService() {
        return new BaseNamedSearchService<>(
                authorPredicateFactory,
                authorRepository,
                authorMapper);
    }

    @Bean
    public SearchService<BaseNamedSearch, List<GenreDTO>> genreNameSearchService() {
        return new BaseNamedSearchService<>(
                genrePredicateFactory,
                genreRepository,
                genreMapper);
    }

    @Bean
    public SearchService<BookSearch, List<BookDTO>> bookNameSearchService() {
        return new BaseNamedSearchService<>(
                bookPredicateFactory,
                bookRepository,
                bookMapper);
    }

}
