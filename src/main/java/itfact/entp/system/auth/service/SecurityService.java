package itfact.entp.system.auth.service;


import itfact.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SecurityService {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
    private Long accessTokenExpiredMs = 1000 * 60 * 60 * 4L;
    private Long refreshTokenExpiredMs = 1000 * 60 * 60 * 24L;

//    public String jwtLogin(String user_id) {
//        return JwtUtil.createAccessToken(user_id, SECRET_KEY, accessTokenExpiredMs);
//    }

    public Map<String, Object> jwtLogin(String user_id) {
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", JwtUtil.createAccessToken(user_id, SECRET_KEY, accessTokenExpiredMs));
        tokenMap.put("refreshToken", JwtUtil.createRefreshToken(user_id, SECRET_KEY, refreshTokenExpiredMs));

        return tokenMap;
    }


}
