package com.bookcrossing.api.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookDTO extends BaseNamedEntityDTO<Long> {

    private String code;

    private List<AuthorDTO> authors;

    private List<GenreDTO> genres;

    private List<UUID> pictures;
}
