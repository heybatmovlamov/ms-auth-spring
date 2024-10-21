package turing.edu.az.ms_auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import turing.edu.az.ms_auth.model.dto.UserDto;
import turing.edu.az.ms_auth.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    public UserDto getUser(String username) {
        return userService.getUser(username);
    }
}
