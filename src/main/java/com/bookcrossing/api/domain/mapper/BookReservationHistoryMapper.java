package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookReservationHistoryDTO;
import com.bookcrossing.api.domain.entity.BookReservationHistory;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface BookReservationHistoryMapper extends BaseMapper<BookReservationHistoryDTO, BookReservationHistory> {
}
