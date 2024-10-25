package turing.edu.az.ms_auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turing.edu.az.ms_auth.jwt.JwtService;

@RequiredArgsConstructor
@Service
public class AuthorizationHelperService  {

    private final JwtService jwtService;

    public String getUsername(String authorization) {
        String token = authorization.substring(7);
        return jwtService.extractUsername(token);
    }
}
