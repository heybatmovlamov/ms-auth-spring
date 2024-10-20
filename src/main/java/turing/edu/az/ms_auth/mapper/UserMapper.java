package turing.edu.az.ms_auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import turing.edu.az.ms_auth.dao.entity.UserEntity;
import turing.edu.az.ms_auth.model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(UserEntity userEntity);

    List<UserDto> entityListToDtoList(List<UserEntity> userEntity);

    UserEntity dtoToEntity(UserDto userDto);

    void updateEntityFromDto(UserDto userDto, @MappingTarget UserEntity userEntity);
}
