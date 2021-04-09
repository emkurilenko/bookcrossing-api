package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.Book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {AuthorMapper.class, GenreMapper.class, FileBookMapper.class, LocationMapper.class,
                BookRateMapper.class},
        unmappedTargetPolicy = IGNORE)
public interface BookMapper extends BaseMapper<BookDTO, Book> {

    @Override
    @Mapping(target = "bookHistories", ignore = true)
    @Mapping(target = "rate", source = "book.id", qualifiedByName = "getBookRating")
    BookDTO mapToDTO(Book book);

    @Override
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "bookHistories", ignore = true)
    @Mapping(target = "updateAt", defaultExpression = "java(java.time.ZonedDateTime.now())")
    Book mapToEntity(BookDTO bookDTO);
}
