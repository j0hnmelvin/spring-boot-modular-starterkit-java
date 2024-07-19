package com.jms.boilerplate.mongodbuserservice.mapper;

import com.jms.boilerplate.mongodbuserservice.domain.UserImpl;
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

//    @Mapping(source = "id", target = "id", qualifiedByName = "convertToLong")
    UserDto toUserDto(UserImpl user);

//    @Named("convertToLong")
//    default Long convertToLong(String stringValue) {
//        // Custom conversion logic here, e.g., handling null values, formatting, etc.
//        return Long.valueOf(stringValue);
//    }
}
