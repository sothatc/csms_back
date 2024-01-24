package itfact.main.auth.service;


import itfact.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityService {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
//    private Long expiredMs = 1000 * 60 * 60l;
    private Long expiredMs = 1000 * 60L;

    public String jwtLogin(String user_id, String user_pwd) {
        return JwtUtil.createJwt(user_id, SECRET_KEY, expiredMs);
    }


}
