package com.bookcrossing.api.domain.dto.search;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookSearch extends BaseNamedSearch {
    private String author;
    private String genre;
}
