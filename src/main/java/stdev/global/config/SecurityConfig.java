package stdev.global.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import stdev.domain.oauth2.infra.filter.FlowfitJWTFilter;
import stdev.domain.oauth2.infra.filter.FlowfitLogoutFilter;
import stdev.global.infra.exception.auth.FlowfitAuthExceptionFilter;
import stdev.global.jwt.domain.repository.JsonWebTokenRepository;
import stdev.global.jwt.domain.repository.KakaoJsonWebTokenRepository;
import stdev.global.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JsonWebTokenRepository jsonWebTokenRepository;
    private final KakaoJsonWebTokenRepository KakaoJsonWebTokenRepository;
    private final List<String> excludedUrls = Arrays.asList("/api/dream","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html","/favicon.ico","/api/reissue", "/api/oauth2/login", "/api/healthcheck", "/api/oauth2/callback");


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                // CORS 설정 파일로 빼기
                .cors((cors) -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of("https://stdev-15-fe.vercel.app", "http://localhost:3000"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Collections.singletonList("Authorization"));
                    config.setMaxAge(3600L);

                    return config;
                }))
                .authorizeHttpRequests((url) -> url
                        .requestMatchers("/api/healthcheck").permitAll()
                        .requestMatchers("/api/oauth2/login").permitAll()
                        .requestMatchers("/api/oauth2/callback").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/favicon.ico","/api/dream").permitAll()
                        .requestMatchers("/api/reissue").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(except -> except
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                        ))
                .addFilterAfter(new FlowfitAuthExceptionFilter(objectMapper), CorsFilter.class)
                .addFilterAfter(new FlowfitJWTFilter(jwtUtil, excludedUrls), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new FlowfitLogoutFilter(jwtUtil, jsonWebTokenRepository, KakaoJsonWebTokenRepository), LogoutFilter.class);

        return http.build();
    }
}