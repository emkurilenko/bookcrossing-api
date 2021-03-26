package com.bookcrossing.api.domain.dto.search;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistorySearch implements SearchHelper {

    private BookSearch bookSearch;
    private UserDTO user;
    private List<BookStatus> statuses;

}
