package turing.edu.az.ms_auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import turing.edu.az.ms_auth.config.RedisConfig;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final AuthorizationHelperService auth;

    public void saveRefreshToken(String token){
        String username = auth.getUsername(token);
        redisTemplate.opsForValue().set(username,token);
    }
    public void getRefreshToken(String token){
        String username = auth.getUsername(token);
        redisTemplate.opsForValue().get(username);
    }
    public void deleteRefreshToken(String token){
        String username = auth.getUsername(token);
        redisTemplate.delete(username);
    }
}
