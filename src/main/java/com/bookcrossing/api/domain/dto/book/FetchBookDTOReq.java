package com.bookcrossing.api.domain.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FetchBookDTOReq {
    private Long bookId;
    private String code;
    @JsonIgnore
    private Long userId;
}
