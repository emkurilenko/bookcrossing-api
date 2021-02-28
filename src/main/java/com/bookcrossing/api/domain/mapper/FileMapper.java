package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.FileDTO;
import com.bookcrossing.api.domain.entity.File;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends BaseMapper<FileDTO, File>{
}
