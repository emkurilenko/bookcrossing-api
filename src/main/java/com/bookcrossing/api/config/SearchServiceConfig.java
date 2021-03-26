package com.bookcrossing.api.config;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.dto.search.UserHistorySearch;
import com.bookcrossing.api.domain.mapper.AuthorMapper;
import com.bookcrossing.api.domain.mapper.BookHistoryMapper;
import com.bookcrossing.api.domain.mapper.BookMapper;
import com.bookcrossing.api.domain.mapper.GenreMapper;
import com.bookcrossing.api.domain.repository.AuthorRepository;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
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

    @Bean
    public SearchService<BaseNamedSearch, List<AuthorDTO>> authorNameSearchService(
            final PredicateFactory<BaseNamedSearch> authorPredicateFactory,
            final AuthorRepository authorRepository,
            final AuthorMapper authorMapper
    ) {
        return new BaseNamedSearchService<>(authorPredicateFactory, authorRepository, authorMapper);
    }

    @Bean
    public SearchService<BaseNamedSearch, List<GenreDTO>> genreNameSearchService(
            final PredicateFactory<BaseNamedSearch> genrePredicateFactory,
            final GenreRepository genreRepository,
            final GenreMapper genreMapper
    ) {
        return new BaseNamedSearchService<>(genrePredicateFactory, genreRepository, genreMapper);
    }

    @Bean
    public SearchService<BookSearch, List<BookDTO>> bookNameSearchService(
            final PredicateFactory<BookSearch> bookPredicateFactory,
            final BookRepository bookRepository,
            final BookMapper bookMapper
    ) {
        return new BaseNamedSearchService<>(bookPredicateFactory, bookRepository, bookMapper);
    }

    @Bean
    public SearchService<UserHistorySearch, List<BookHistoryDTO>> userHistorySearchSearchService(
            final PredicateFactory<UserHistorySearch> predicate,
            final BookHistoryRepository repository,
            final BookHistoryMapper mapper
    ) {
        return new BaseNamedSearchService<>(predicate, repository, mapper);
    }
}
