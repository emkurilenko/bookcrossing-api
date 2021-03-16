package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookReservationHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookReservationDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookReservationMapper {

    @Mapping(target = "bookId", source = "dto.book.id")
    BookReservationDTO mapToDTO(BookReservationHistoryDTO dto);
}
