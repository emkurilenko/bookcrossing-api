package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BookRatingDTO;

public interface BookRatingService extends BaseService<BookRatingDTO, Long> {

    Double getUserRating(Long userId);

    Double getBookRating(Long bookId);

}
