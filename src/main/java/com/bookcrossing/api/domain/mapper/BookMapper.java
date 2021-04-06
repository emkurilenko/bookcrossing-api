package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.Book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {AuthorMapper.class, GenreMapper.class, FileBookMapper.class, LocationMapper.class,
                BookStatusMapper.class},
        unmappedTargetPolicy = IGNORE)
public interface BookMapper extends BaseMapper<BookDTO, Book> {


    @Override
    @Mapping(target = "status", source = "book", qualifiedByName = "getLastStatus")
    @Mapping(target = "bookHistories", ignore = true)
    BookDTO mapToDTO(Book book);

//    @Named("getLastStatus")
//    default BookStatus getLastStatus(List<BookHistory> histories) {
//        return ofNullable(histories)
//                .orElseGet(List::of)
//                .stream()
//                .filter(item -> !ignoreStatuses.contains(item.getStatus()))
//                .max(Comparator.comparing(BookHistory::getCreatedDate))
//                .map(BookHistory::getStatus)
//                .orElse(BookStatus.UNKNOWN);
//    }
}
