package itfact.entp.system.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtUtils {
//    private static final String SECRET_KEY = "mySecretKey";

    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

    public boolean authenticateUser(String username, String password) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SECRET_KEY = " + SECRET_KEY);

        /** ToDo: DB의 정보와 일치하는지 확인하는 작업 */
        return username.equals("username") && password.equals("password");
    }

    /**
     * JWT생성
     */
    public String generateJWT(String username) {
        /** 토큰 만료 시간 설정 */
        long expirationTime = System.currentTimeMillis() + 3600000;

        /** JWT 생성 */
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * JWT 검증
     */
    public boolean verifyJWT(String token) {
        try {
            /** 토큰 검증 */
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
