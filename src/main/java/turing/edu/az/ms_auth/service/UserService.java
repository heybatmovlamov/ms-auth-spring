package turing.edu.az.ms_auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turing.edu.az.ms_auth.dao.entity.UserEntity;
import turing.edu.az.ms_auth.dao.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

        public List<UserEntity> users (){//access only admin after
         return  userRepository.findAll();
        }

        public UserEntity getUser (String username){
            return  userRepository.findByUsername(username);
        }

}
