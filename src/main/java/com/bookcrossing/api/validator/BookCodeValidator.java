package com.bookcrossing.api.validator;

import com.bookcrossing.api.domain.dto.book.TakeAwayBookReq;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookCodeValidator implements Validator<TakeAwayBookReq> {

    private final BookRepository bookRepository;

    @Autowired
    public BookCodeValidator(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void validate(TakeAwayBookReq value) {
        Book book = bookRepository.findById(value.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("book.not.found"));//TODO throw NotFoundException

        if (!book.getCode().equals(value.getCode())) {
            throw new IllegalStateException("code.not.matched");
        }

    }

    @Override
    public ValidatorType type() {
        return ValidatorType.BOOK_CODE;
    }
}
