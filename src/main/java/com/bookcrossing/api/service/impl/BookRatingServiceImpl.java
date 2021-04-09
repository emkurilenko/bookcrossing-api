package com.bookcrossing.api.service.impl;


import com.bookcrossing.api.domain.dto.BookRatingDTO;
import com.bookcrossing.api.domain.entity.BookRating;
import com.bookcrossing.api.domain.entity.QBookRating;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BookRatingRepository;
import com.bookcrossing.api.service.BookRatingService;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookRatingServiceImpl extends DefaultBaseService<BookRatingDTO, BookRating, Long>
        implements BookRatingService {

    private static final QBookRating QBR = QBookRating.bookRating;

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public BookRatingServiceImpl(
            BaseMapper<BookRatingDTO, BookRating> mapper,
            JPAQueryFactory jpaQueryFactory,
            BookRatingRepository bookRatingRepository) {
        super(mapper, bookRatingRepository);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Double getUserRating(Long userId) {
        return jpaQueryFactory.from(QBR)
                .where(QBR.owner.id.eq(userId))
                .transform(GroupBy.groupBy(QBR.owner.id).as(GroupBy.avg(QBR.rate)))
                .get(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getBookRating(Long bookId) {
        return jpaQueryFactory.from(QBR)
                .where(QBR.book.id.eq(bookId))
                .transform(GroupBy.groupBy(QBR.book.id).as(GroupBy.avg(QBR.rate)))
                .get(bookId);
    }
}
