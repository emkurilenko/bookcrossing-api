package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookRatingDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookRating;
import com.bookcrossing.api.domain.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookRatingMapper extends BaseMapper<BookRatingDTO, BookRating> {

    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "user", source = "value.userId", qualifiedByName = "mapUser")
    @Mapping(target = "owner", source = "value.ownerId", qualifiedByName = "mapUser")
    @Mapping(target = "book", source = "value.bookId", qualifiedByName = "mapBook")
    @Mapping(target = "createdDate", defaultExpression = "java(java.time.ZonedDateTime.now())")
    @Override
    BookRating mapToEntity(BookRatingDTO value);

    @Override
    @Mapping(target = "userId", source = "value.user.id")
    @Mapping(target = "ownerId", source = "value.owner.id")
    @Mapping(target = "bookId", source = "value.book.id")
    BookRatingDTO mapToDTO(BookRating value);

    @Named("mapUser")
    default User mapUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    @Named("mapBook")
    default Book mapBook(Long bookId) {
        Book book = new Book();
        book.setId(bookId);
        return book;
    }
}
