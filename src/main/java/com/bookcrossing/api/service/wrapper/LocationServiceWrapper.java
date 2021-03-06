package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.Location;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceWrapper extends BaseBaseServiceWrapper<LocationDTO, Location, Long> {

    @Autowired
    public LocationServiceWrapper(BaseService<LocationDTO, Location, Long> service) {
        super(service);
    }
}
