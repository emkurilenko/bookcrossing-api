package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.Location;

public interface LocationService extends BaseService<LocationDTO, Location, Long> {
    LocationDTO findByBookId(Long bookdId);
}
