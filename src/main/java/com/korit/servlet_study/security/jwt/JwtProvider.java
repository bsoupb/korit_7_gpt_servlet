package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
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
    public String generateToken(User user) {    // User 객체를 받아 JWT 토큰 반환
        return Jwts.builder()
                .claim("userId", user.getUserId())  // 토큰의 페이로드에 userId 커스텀 클레임을 추가
                .setExpiration(getExpireDate())     // 토큰의 만료 시간을 설정
                .signWith(key, SignatureAlgorithm.HS256)    // 지정된 키를 사용하여 HMAC SHA-256 알고리즘으로 토큰을 서명하여 무결성과 인증을 보장
                .compact();     // JWT 빌더 인스턴스를 URL 안전한 문자열로 변환하여 실제 JWT 토큰을 생성
    }

    public Claims parseToken(String token) {        // JWT 문자열을 파라미터로 받아 클레임(Claims)을 반환
        Claims claims = null;
        try {
            JwtParser jwtParser = Jwts.parserBuilder()   // JWT 파싱
                                    .setSigningKey(key)     // 토큰의 서명을 검증하기 위해 사용할 키를 설정
                                    .build();

            claims = jwtParser
                    .parseClaimsJws(removeBearer(token))    // 빌더를 완성하여 JWT 토큰을 파싱하고 서명을 검증
                    .getBody();     // 파싱된 JWT의 페이로드에서 클레임을 가져옴
        } catch(Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private String removeBearer(String bearerToken) {
        String accessToken = null;
        final String BEARER_KEYWORD = "Bearer ";
        if(bearerToken.startsWith(BEARER_KEYWORD)){
            accessToken = bearerToken.substring(BEARER_KEYWORD.length());
        }
        return accessToken;
    }
}
