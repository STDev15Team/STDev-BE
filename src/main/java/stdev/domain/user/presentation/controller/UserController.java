package stdev.domain.user.presentation.controller;

import flowfit.domain.user.application.service.UserService;
import flowfit.domain.user.domain.repository.UserRepository;
import flowfit.domain.user.presentation.dto.req.UserJoinDto;
import flowfit.domain.user.presentation.dto.req.UserLoginDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;
    @PostMapping("/join")
    public void UserJoin(@RequestBody UserJoinDto joinDto, HttpServletResponse response) throws IOException {
        userService.userJoin(joinDto, response);
    }

    @PostMapping("/login")
    public void UserJoin(@RequestBody UserLoginDto loginDto, HttpServletResponse response) throws IOException {
        userService.userLoin(loginDto,response);
    }




}