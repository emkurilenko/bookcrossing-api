package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.entity.BookHistory;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface BookHistoryMapper extends BaseMapper<BookHistoryDTO, BookHistory> {

}
