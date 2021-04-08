package com.bookcrossing.api.domain.dto.book;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.BaseNamedEntityDTO;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookDTO extends BaseNamedEntityDTO<Long> {

    private ZonedDateTime updateAt;

    private String code;

    private BookStatus status;

    private List<AuthorDTO> authors;

    private List<GenreDTO> genres;

    private List<UUID> pictures;

    private LocationDTO location;

    @JsonIgnore
    private List<BookHistoryDTO> bookHistories;

    private Double rate;
}
