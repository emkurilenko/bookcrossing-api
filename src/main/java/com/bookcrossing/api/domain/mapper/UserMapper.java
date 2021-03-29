package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {FileBookMapper.class})
public interface UserMapper extends BaseMapper<UserDTO, User> {
}
