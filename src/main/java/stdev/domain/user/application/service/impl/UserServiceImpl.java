package stdev.domain.user.application.service.impl;


import stdev.domain.oauth2.application.service.CreateAccessTokenAndRefreshTokenService;
import stdev.domain.user.application.service.UserService;

import stdev.domain.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;

//@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CreateAccessTokenAndRefreshTokenService createAccessTokenAndRefreshTokenService;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


}
