package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.Book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {AuthorMapper.class, GenreMapper.class, FileBookMapper.class, LocationMapper.class},
        unmappedTargetPolicy = IGNORE)
public interface BookMapper extends BaseMapper<BookDTO, Book> {

    @Override
    @Mapping(target = "bookHistories", ignore = true)
    BookDTO mapToDTO(Book book);
}
