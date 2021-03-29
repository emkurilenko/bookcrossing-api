package com.bookcrossing.api.domain.dto.search;

import lombok.Builder;
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
    private String author;
    private String genre;
    private List<Long> locationIds;
}
