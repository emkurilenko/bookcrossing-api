package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.Location;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.LocationRepository;
import com.bookcrossing.api.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends DefaultBaseService<LocationDTO, Location, Long> implements
        LocationService {

    private final BaseMapper<LocationDTO, Location> mapper;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(
            final BaseMapper<LocationDTO, Location> mapper,
            final LocationRepository locationRepository) {
        super(mapper, locationRepository);
        this.mapper = mapper;
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDTO findByBookId(Long bookId) {
//        return locationRepository.findByBooksIdIn(bookId)
//                .map(mapper::mapToDTO)
//                .orElse(null);
        return null;
    }
}
