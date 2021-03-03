package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.Location;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookIdMapper {

    @Named("getBookId")
    default Long getBookId(Book book) {
        return book.getId();
    }

    @Named("getBookById")
    default Book getBookById(Long bookId) {
        Book book = new Book();
        book.setId(bookId);
        return book;
    }
}
