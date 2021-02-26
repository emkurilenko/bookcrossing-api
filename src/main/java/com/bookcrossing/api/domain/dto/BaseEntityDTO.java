package com.bookcrossing.api.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntityDTO<ID> implements BaseDTO<ID> {
    private ID id;
}
