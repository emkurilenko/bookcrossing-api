package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookUserHistoryDTO;
import com.bookcrossing.api.domain.entity.BookHistory;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface BookUserHistoryMapper {

    BookUserHistoryDTO mapToDTO(BookHistory value);
}
