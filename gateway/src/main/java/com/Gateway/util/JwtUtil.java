package com.Gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {
    public static final String SECRET = "warsKoderSecretKeyzeddd8t7t4v5f8d8e9z9z6f5f5g8t8yr8e8e5z5s2d22f1g5t8r8e9z9z9e65r6ee8e7e4e1e2fds31df2er2f5f2";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }



    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
