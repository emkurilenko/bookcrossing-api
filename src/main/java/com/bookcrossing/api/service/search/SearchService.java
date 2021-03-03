package com.bookcrossing.api.service.search;

public interface SearchService<SEARCH, RES> {

    RES search(SEARCH searchHelper);
}
