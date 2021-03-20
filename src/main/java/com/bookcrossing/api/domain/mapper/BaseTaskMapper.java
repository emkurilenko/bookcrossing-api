package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BaseTaskDTO;
import com.bookcrossing.api.domain.entity.BaseTask;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseTaskMapper extends BaseMapper<BaseTaskDTO, BaseTask> {
}
