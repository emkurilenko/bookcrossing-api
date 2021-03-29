package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.Location;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {FileBookMapper.class, BookIdMapper.class, BookMapper.class},
        nullValueMappingStrategy = RETURN_DEFAULT)
public interface LocationMapper extends BaseMapper<LocationDTO, Location> {

    @Override
    @Mapping(target = "books", ignore = true)
    LocationDTO mapToDTO(Location location);

    @Override
    @Mapping(target = "isDeleted", ignore = true)
    Location mapToEntity(LocationDTO location);

}
