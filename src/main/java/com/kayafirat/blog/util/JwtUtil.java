package com.kayafirat.blog.util;

import com.kayafirat.blog.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public Long extractUserID(String token) {
        return Long.valueOf(extractClaim(token, Claims::getSubject));
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities());
        return createToken(claims, user.getId());
    }

    public String generateToken(String email,Long time) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,email,time);
    }
    private String createToken(Map<String, Object> claims, String email,Long time)  {
        return Jwts.builder().setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + time )) // 30 min
                .setAudience("kayafirat.com")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    private String createToken(Map<String, Object> claims, Long id)  {
        return Jwts.builder().setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000 * 30 * 30))
                .setAudience("kayafirat.com")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, User user) {
        final Long id = extractUserID(token);
        return (id.equals(user.getId()) && !isTokenExpired(token));
    }


}