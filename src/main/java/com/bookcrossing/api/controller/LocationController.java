package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.search.LocationSearch;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.facade.BookFacade;
import com.bookcrossing.api.service.search.SearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstants.LOCATION_MAPPING)
public class LocationController extends AbstractSearchController<LocationDTO, Long, LocationSearch> {

    private final BaseService<LocationDTO, Long> locationService;
    private final BookFacade bookFacade;

    @Autowired
    public LocationController(
            SearchService<LocationSearch, List<LocationDTO>> searchService,
            BaseService<LocationDTO, Long> locationService,
            BookFacade bookFacade) {
        super(locationService, searchService);
        this.locationService = locationService;
        this.bookFacade = bookFacade;
    }

    @Override
    @PostMapping
    public LocationDTO persist(@RequestBody LocationDTO value) {
        return locationService.persist(value);
    }

    @GetMapping("/{locationId}/books")
    public List<BookDTO> findBooksByLocation(@PathVariable Long locationId) {
        return bookFacade.findBooksByLocationId(locationId);
    }

}
