package com.jms.boilerplate.integration.kafka.avro.mapper;

import com.jms.boilerplate.integration.kafka.avro.event.CreateUserEvent;
import com.jms.boilerplate.userservice.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserDto toUserDto(CreateUserEvent createUserEvent);
}
