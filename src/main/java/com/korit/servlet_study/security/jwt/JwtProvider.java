package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {
    private Key key;
    private static JwtProvider instance;

    private JwtProvider() {
        final String SECRET = "e656a4adaf309fcde90666a1fe211609187093e5ba53ad65d309f7ede16f5a962eebde916393c7223dc0f9900fbd89c3b8c7a0e0063e06b9c4ae93ff9546a36e2073c8109752c53d2f7134f0b79ee040037466d3ff3d3239ae6a13efd14b9942fe9f3d7b88e0f2e5d5c2cb82ee530fabab0a936ac88380cb13097f30fbcca90ee524df17b735b4c20dd3a5a502e123f6e1cc57f54473a7799534d160cbcb788f737d4791607f37874c379d02564f96a56a0b46528658713232a1a687cf5f4f8783bbbf0638564970e1a42f54eaac32d495e98df490571d7ac9b5ddd0336d1b79c8e9c9a2559af5e97a66a8aa1f9f4691fb22dfbeb9013b1d5afbf9f5196e08ea";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public static JwtProvider getInstance(){
        if(instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpireDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365));
    }
    
    // 토큰 생성
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .setExpiration(getExpireDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
