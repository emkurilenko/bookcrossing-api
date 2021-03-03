package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.LocationDTO;
import com.bookcrossing.api.domain.entity.Location;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {FileBookMapper.class, BookIdMapper.class},
        nullValueMappingStrategy = RETURN_DEFAULT)
public interface LocationMapper extends BaseMapper<LocationDTO, Location> {

    @Override
    @Mapping(target = "bookId", source = "location.book", qualifiedByName = "getBookId")
    LocationDTO mapToDTO(Location location);

    @Override
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "book", source = "bookId", qualifiedByName = "getBookById")
    Location mapToEntity(LocationDTO location);
}
