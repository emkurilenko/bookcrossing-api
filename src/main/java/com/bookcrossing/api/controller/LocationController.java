package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstants.BOOK_BOOK_ID_LOCATION_MAPPING)
public class LocationController {

    private final BaseServiceWrapper<LocationDTO, Long> locationService;

    @Autowired
    public LocationController(final BaseServiceWrapper<LocationDTO, Long> locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public LocationDTO persist(
            @PathVariable("bookId") Long bookId,
            @RequestBody LocationDTO value) {
        value.setBookId(bookId);
        return locationService.persist(value);
    }
}
