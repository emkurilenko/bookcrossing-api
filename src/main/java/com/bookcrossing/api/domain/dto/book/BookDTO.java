package com.bookcrossing.api.domain.dto.book;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.BaseNamedEntityDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.LocationDTO;
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

    private LocationDTO location;
}
