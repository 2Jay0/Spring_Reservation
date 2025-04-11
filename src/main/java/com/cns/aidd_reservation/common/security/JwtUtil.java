package com.cns.aidd_reservation.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 시크릿 키는 Base64 인코딩된 256비트 이상 값 권장
    private final String secret = "mySecretKey12345678901234567890123456789012"; // 예시 (실제로는 외부 config에서 불러오기)
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    // 토큰에서 subject(사용자 ID 등) 추출
    public String extractSubject(String token) {
        return parseClaims(token).getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired");
        } catch (JwtException e) {
            System.out.println("Invalid token");
        }
        return false;
    }

    // 내부용 - 클레임 파싱
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
