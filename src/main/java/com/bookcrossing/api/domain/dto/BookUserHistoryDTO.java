package com.bookcrossing.api.domain.dto;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BookUserHistoryDTO extends BaseEntityDTO<Long> {
    private BookDTO book;
    private BookStatus status;
}
