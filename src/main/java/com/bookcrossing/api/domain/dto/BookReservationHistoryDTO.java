package com.bookcrossing.api.domain.dto;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.BookStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookReservationHistoryDTO extends BaseEntityDTO<Long> {

    private ZonedDateTime createdDate;

    private ZonedDateTime expiredAt;

    private UserDTO user;

    private BookDTO book;

    private BookStatus status; //TODO use enum

}
