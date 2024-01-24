package itfact.common.dataSource.config;


import itfact.main.auth.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity // 스프링시큐리티가 모든 api요청에 인증이 필요하다는 디폴트를 설정.
@RequiredArgsConstructor
@PropertySource("classpath:/application.yml")
public class SecurityConfig {   //extends로 오버라이드하는 방식은 스프린부트 버전이 올라가면서 지양됨.

    private final SecurityService securityService;
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Bean(name = "securityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                // 토큰을 사용하기 때문에 csrf 설정 disable
                .csrf(csrf -> csrf.disable())
                .cors().and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/user/login").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .and()
                .addFilterBefore(new JwtFilter(securityService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .getOrBuild();
    }


//        httpSecurity
//                .csrf().disable()
//                .formLogin().disable();
//        return httpSecurity
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers("/user/login").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .build();

}
