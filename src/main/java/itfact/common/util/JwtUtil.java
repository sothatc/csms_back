package itfact.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static String createAccessToken(String user_id, String secretKey, Long accessTokenExpiredMs) {
        Claims claims = Jwts.claims();
        claims.put("user_id", user_id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static String createRefreshToken(String user_id, String secretKey, Long refreshTokenExpiredMs) {
        Claims claims = Jwts.claims();
        claims.put("user_id", user_id);

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before(new Date());
    }

    public static String getUserId(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().get("user_id", String.class);
    }
}
