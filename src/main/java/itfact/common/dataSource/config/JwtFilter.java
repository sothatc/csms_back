package itfact.common.dataSource.config;

import itfact.common.util.JwtUtil;
import itfact.entp.system.auth.service.SecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final SecurityService securityService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization이 잘못되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //Token 꺼내기
        String token = authorization.split(" ")[1];

        //Token이 만료되었는지
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //Token에서 user_id 꺼내기
        String userId = JwtUtil.getUserId(token, secretKey);
        log.debug("userId: {}", userId);

        /** 권한 부여 */
        UsernamePasswordAuthenticationToken authenticationToken =
                /**
                 * 1. 임시로 "USER"라는 권한을 부여
                 * 2. hasRole() 메서드, @Secured, @PreAuthorize 등을 사용하여 특정 메서드나 엔드포인트에 접근 제한 가능.
                 * 3. Ex) @Secured({"ROLE_AUTHOR", "ROLE_ADMIN"})
                 *     public void writeBlog() {
                 *         // 블로그를 작성하는 로직
                 *     }
                 */
                new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));

        /** Detail을 넣어줌. */
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}
