package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.Location;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends DefaultBaseService<LocationDTO, Location, Long> {

    @Autowired
    public LocationService(
            final BaseMapper<LocationDTO, Location> mapper,
            final BaseCrudRepository<Location, Long> repository) {
        super(mapper, repository);
    }
}
