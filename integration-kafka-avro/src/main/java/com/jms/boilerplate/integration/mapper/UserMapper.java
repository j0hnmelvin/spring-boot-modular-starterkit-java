package com.jms.boilerplate.integration.mapper;

import com.jms.boilerplate.integration.event.CreateUserEvent;
import com.jms.boilerplate.userservice.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(CreateUserEvent createUserEvent);
}
