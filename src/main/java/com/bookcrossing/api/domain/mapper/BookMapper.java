package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;

import java.util.Comparator;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        uses = {AuthorMapper.class, GenreMapper.class, FileBookMapper.class, LocationMapper.class},
        unmappedTargetPolicy = IGNORE)
public interface BookMapper extends BaseMapper<BookDTO, Book> {

    List<BookStatus> ignoreStatuses = List.of(BookStatus.CANCELED);

    @Override
    @Mapping(target = "bookHistories", ignore = true)
    @Mapping(target = "status", source = "book.bookHistories", qualifiedByName = "getLastStatus")
    BookDTO mapToDTO(Book book);

    @Named("getLastStatus")
    default BookStatus getLastStatus(List<BookHistory> histories) {
        return histories.stream()
                .filter(item -> !ignoreStatuses.contains(item.getStatus()))
                .max(Comparator.comparing(BookHistory::getCreatedDate))
                .map(BookHistory::getStatus)
                .orElse(BookStatus.UNKNOWN);
    }
}
