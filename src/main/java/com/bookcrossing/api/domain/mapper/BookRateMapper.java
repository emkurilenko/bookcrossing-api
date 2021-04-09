package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.service.BookRatingService;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookRateMapper {

    private final BookRatingService bookRatingService;

    @Autowired
    public BookRateMapper(BookRatingService bookRatingService) {
        this.bookRatingService = bookRatingService;
    }

    @Named("getBookRating")
    public Double getBookRating(Long bookId) {
        return bookRatingService.getBookRating(bookId);
    }

}
