package com.samanecorp.thymeleaf1.mapper;

import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);
    UserEntity toUserEntity(UserDto userDto);
    List<UserDto> listUserEntityToListUserDto(List<UserEntity> userEntities);
}
