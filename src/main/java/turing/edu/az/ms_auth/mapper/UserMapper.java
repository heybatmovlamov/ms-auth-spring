package turing.edu.az.ms_auth.mapper;

import org.springframework.stereotype.Component;
import turing.edu.az.ms_auth.dao.entity.UserEntity;
import turing.edu.az.ms_auth.model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto entityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());

        return userDto;
    }

    public List<UserDto> entityesToDtos(List<UserEntity> userEntityList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userDtoList.add(entityToDto(userEntity));
        }
        return userDtoList;
    }

    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());

        return userEntity;
    }


}
