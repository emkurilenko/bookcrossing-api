package com.bookcrossing.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class LocationDTO extends BaseEntityDTO<Long> {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private List<UUID> pictures;
    @JsonIgnore
    private Long bookId;
}
