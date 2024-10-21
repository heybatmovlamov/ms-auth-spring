package turing.edu.az.ms_auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turing.edu.az.ms_auth.dao.entity.UserEntity;
import turing.edu.az.ms_auth.dao.repository.UserRepository;
import turing.edu.az.ms_auth.mapper.UserMapper;
import turing.edu.az.ms_auth.model.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

        public List<UserDto> getAllUsers (){//access only admin after
            var users = userRepository.findAll();
            return  userMapper.entityListToDtoList(users);
        }

        public UserDto getUser (String username){
            var user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User Not Found"));
            return  userMapper.entityToDto(user);
        }

}
