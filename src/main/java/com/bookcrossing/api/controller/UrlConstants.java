package com.bookcrossing.api.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlConstants {
    public static final String AUTHOR_MAPPING = "/author";
    public static final String GENRE_MAPPING = "/genre";
    public static final String SEARCH_MAPPING = "/search";
    public static final String ID_MAPPING = "/{id}";
}
