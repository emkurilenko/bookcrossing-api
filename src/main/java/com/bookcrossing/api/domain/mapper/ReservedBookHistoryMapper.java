package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookBookedHistoryDTO;
import com.bookcrossing.api.domain.entity.BookBookingHistory;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface ReservedBookHistoryMapper extends BaseMapper<BookBookedHistoryDTO, BookBookingHistory> {
}
