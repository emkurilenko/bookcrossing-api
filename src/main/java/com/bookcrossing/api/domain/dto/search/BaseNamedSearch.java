package com.bookcrossing.api.domain.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class BaseNamedSearch implements SearchHelper {

    private String name;
}
