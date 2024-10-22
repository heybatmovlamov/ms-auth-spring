package turing.edu.az.ms_auth.model.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
