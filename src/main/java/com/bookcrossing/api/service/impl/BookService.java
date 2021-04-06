package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.LocationService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService extends DefaultBaseService<BookDTO, Book, Long> {

    private final BaseService<AuthorDTO, Long> authorBaseService;
    private final BaseService<GenreDTO, Long> genreBaseService;
    private final LocationService locationService;
    private final BookHistoryService bookHistoryService;

    public BookService(
            final BaseMapper<BookDTO, Book> mapper,
            final BaseCrudRepository<Book, Long> repository,
            final BaseService<AuthorDTO, Long> authorBaseService,
            final BaseService<GenreDTO, Long> genreBaseService,
            final LocationService locationService,
            final BookHistoryService bookHistoryService) {
        super(mapper, repository);
        this.authorBaseService = authorBaseService;
        this.genreBaseService = genreBaseService;
        this.locationService = locationService;
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    @Transactional
    public BookDTO persist(BookDTO value) {

        if (value.getId() != null) {
            List<BookHistoryDTO> bookHistoryDTOS = bookHistoryService.findByBookId(value.getId());
            value.setBookHistories(bookHistoryDTOS);

//            LocationDTO locationDTO = locationService.findById(value.getId());
//            LocationDTO location = value.getLocation();
//            location.setId(locationDTO.getId());
//            value.setLocation(location);
        }

        if (value.getCode() == null) {
            value.setCode(generateUniqueCode());
        }

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

        BookDTO persist = super.persist(value);

        return persist;
    }

    private String generateUniqueCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            int value = random.nextInt(999);
            stringBuilder.append(value);
            if (i != 3) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }
}
