package stdev.domain.oauth2.application.service.impl;


import flowfit.domain.oauth2.application.service.*;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import flowfit.domain.user.domain.entity.Role;
import flowfit.global.jwt.domain.entity.KakaoJsonWebToken;
import flowfit.global.jwt.domain.repository.KakaoJsonWebTokenRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KakaoLoginServiceImpl implements KakaoLoginService {

    private final  KakaoAccessTokenAndRefreshTokenService KakaoAccessTokenAndRefreshTokenService;
    private final KakaoUserService KakaoUserService;
    private final CreateAccessTokenAndRefreshTokenService createAccessTokenAndRefreshTokenService;
    private final KakaoUserCreateService KakaoUserCreateService;
    private final KakaoJsonWebTokenRepository kakaoJsonWebTokenRepository;

    @Override
    public Map<String, String> login(String code, HttpServletResponse response) throws IOException {
        OAuth2TokenResponse oAuth2TokenResponse = KakaoAccessTokenAndRefreshTokenService.getAccessTokenAndRefreshToken(code);

        OAuth2UserResponse oAuth2UserResponse = KakaoUserService.getUser(oAuth2TokenResponse.accessToken());

        Map<String, String> values = KakaoUserCreateService.createKakaoUser(oAuth2TokenResponse,  oAuth2UserResponse);

        String userId = values.get("id");
        Role role = Role.valueOf(values.get("role"));
        String userEmail = values.get("email");

        // Kakao 토큰 저장
        KakaoJsonWebToken KakaoToken = KakaoJsonWebToken.builder()
                .userId(userId)
                .accessToken(oAuth2TokenResponse.accessToken())
                .refreshToken(oAuth2TokenResponse.refreshToken())
                .build();
        kakaoJsonWebTokenRepository.save(KakaoToken);

        Map<String, String> tokens = createAccessTokenAndRefreshTokenService.createAccessTokenAndRefreshToken(userId, role, userEmail);

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokens.get("access_token"));
        response.addHeader(HttpHeaders.SET_COOKIE, tokens.get("refresh_token_cookie"));

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        response.getWriter().write("Successfully Login");
        return tokens;
    }
}
