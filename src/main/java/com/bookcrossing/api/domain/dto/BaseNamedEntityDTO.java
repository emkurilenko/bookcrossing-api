package com.bookcrossing.api.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseNamedEntityDTO<ID> extends BaseEntityDTO<ID> {
    private String name;
}
