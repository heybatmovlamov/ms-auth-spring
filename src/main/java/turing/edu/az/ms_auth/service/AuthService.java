package turing.edu.az.ms_auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import turing.edu.az.ms_auth.dao.repository.UserRepository;
import turing.edu.az.ms_auth.jwt.JwtService;
import turing.edu.az.ms_auth.mapper.UserMapper;
import turing.edu.az.ms_auth.model.dto.TokenDto;
import turing.edu.az.ms_auth.model.dto.UserDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RedisService redisService;
    public UserDto register(@RequestBody UserDto userDto) {
        var userEntity = userMapper.dtoToEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userMapper.entityToDto(userRepository.save(userEntity));
    }

    public TokenDto login(@RequestBody UserDto userDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        authenticationManager.authenticate(authenticationToken);
        TokenDto generatedToken = jwtService.generateToken(userDto.getUsername());
        redisService.saveRefreshToken(generatedToken.getRefreshToken());
        return generatedToken;
    }
    public void  logout(@RequestHeader("Authorization") String token){
        redisService.deleteRefreshToken(token);
    }
}
