package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookBookedHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookBookingDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookReservationMapper {

    @Mapping(target = "bookId", source = "dto.book.id")
    BookBookingDTO mapToDTO(BookBookedHistoryDTO dto);
}
