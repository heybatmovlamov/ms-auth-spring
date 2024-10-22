package turing.edu.az.ms_auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import turing.edu.az.ms_auth.dao.entity.UserEntity;
import turing.edu.az.ms_auth.model.dto.TokenDto;
import turing.edu.az.ms_auth.model.dto.UserDto;
import turing.edu.az.ms_auth.service.AuthService;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto){
        return authService.register(userDto);
    }
    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto){
        return authService.login(userDto);
    }

}
