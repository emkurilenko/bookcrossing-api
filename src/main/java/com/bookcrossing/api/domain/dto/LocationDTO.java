package com.bookcrossing.api.domain.dto;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LocationDTO extends BaseEntityDTO<Long> {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    @JsonIgnore
    private List<BookDTO> books;
}
