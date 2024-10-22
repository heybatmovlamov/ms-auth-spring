package turing.edu.az.ms_auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import turing.edu.az.ms_auth.model.dto.TokenDto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "jwttutorial";

    private Claims extractALLClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractALLClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public  String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public TokenDto generateToken(String username) {
        TokenDto tokenDto = new TokenDto();
        Map<String ,Object> claims = new HashMap<>();

        String accessToken = createAccessToken(claims, username);
        String refreshToken = createRefreshToken(username);
        tokenDto.setAccessToken(accessToken);
        tokenDto.setRefreshToken(refreshToken);
    }

    private String createAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (15 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS256 ,SECRET_KEY)
                .compact();
    }
    public String createRefreshToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (60*60*1000)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


}