package com.bookcrossing.api.config;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.mapper.AuthorMapper;
import com.bookcrossing.api.domain.mapper.GenreMapper;
import com.bookcrossing.api.domain.repository.AuthorRepository;
import com.bookcrossing.api.domain.repository.GenreRepository;
import com.bookcrossing.api.service.search.BaseNamedSearchService;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.search.factory.PredicateFactory;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchServiceConfig {

    //TODO think about factory or create separate services
    private final PredicateFactory<BaseNamedSearch> namedSearchPredicateFactory;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;

    public SearchServiceConfig(
            final PredicateFactory<BaseNamedSearch> namedSearchPredicateFactory,
            final GenreRepository genreRepository,
            final AuthorRepository authorRepository,
            final AuthorMapper authorMapper,
            final GenreMapper genreMapper) {
        this.namedSearchPredicateFactory = namedSearchPredicateFactory;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
    }

    @Bean
    public SearchService<BaseNamedSearch, List<AuthorDTO>> authorNameSearchService() {
        return new BaseNamedSearchService<>(
                namedSearchPredicateFactory,
                authorRepository,
                authorMapper);
    }

    @Bean
    public SearchService<BaseNamedSearch, List<GenreDTO>> genreNameSearchService() {
        return new BaseNamedSearchService<>(
                namedSearchPredicateFactory,
                genreRepository,
                genreMapper);
    }
}
