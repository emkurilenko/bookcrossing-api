package com.bookcrossing.api.domain.dto.search;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookSearch extends BaseNamedSearch {
    private List<Long> authors;
    private List<Long> genres;
    private List<Long> locationIds;
}
