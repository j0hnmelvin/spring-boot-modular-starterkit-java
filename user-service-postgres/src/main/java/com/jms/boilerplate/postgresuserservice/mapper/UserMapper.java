package com.jms.boilerplate.postgresuserservice.mapper;

import com.jms.boilerplate.postgresuserservice.domain.UserImpl;
import com.jms.boilerplate.userservice.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserImpl toUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromDto(UserDto userDto, @MappingTarget UserImpl user);

    UserDto toUserDto(UserImpl user);
}
