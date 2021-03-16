package com.bookcrossing.api.domain.dto.book;

import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class BookReservationDTO extends BaseEntityDTO<Long> {

    private Long bookId;
    private ZonedDateTime createdDate;
    private ZonedDateTime expiredAt;
}
