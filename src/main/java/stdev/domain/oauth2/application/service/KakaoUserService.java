package stdev.domain.oauth2.application.service;

import flowfit.domain.oauth2.presentation.dto.response.OAuth2UserResponse;

public interface KakaoUserService {
    OAuth2UserResponse getUser(String accessToken);
}
