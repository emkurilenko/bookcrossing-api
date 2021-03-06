package com.bookcrossing.api.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlConstants {
    public static final String BOOK_MAPPING = "/book";
    public static final String AUTHOR_MAPPING = "/author";
    public static final String GENRE_MAPPING = "/genre";
    public static final String SEARCH_MAPPING = "/search";
    public static final String FILE_MAPPING = "/file";
    public static final String ID_MAPPING = "/{id}";
    public static final String FETCH_FILE_MAPPING = "/{id}/fetch";
    public static final String LOCATION_MAPPING = "/location";
    public static final String BOOK_ID_BOOKING_MAPPING = "/{bookId}/booking";
    public static final String TAKE_AWAY_BOOK_MAPPING = "/take-away";
    public static final String BOOK_HISTORY_REQUEST_MAPPING = "/book/history";
    public static final String BOOKED_REQUEST_MAPPING = "/booked";
    public static final String CHANGE_PASSWORD_REQUEST_MAPPING = "/change-password";

}
