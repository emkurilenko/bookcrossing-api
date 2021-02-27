package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.BookDTO;
import com.bookcrossing.api.domain.entity.Book;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {AuthorMapper.class, GenreMapper.class},
        unmappedTargetPolicy = IGNORE,
        nullValueMappingStrategy = RETURN_DEFAULT)
public interface BookMapper extends BaseMapper<BookDTO, Book> {
}
