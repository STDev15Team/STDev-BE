package stdev.domain.user.presentation.controller;

import stdev.domain.user.application.service.UserService;
import stdev.domain.user.domain.repository.UserRepository;
import stdev.domain.user.presentation.dto.req.UserJoinDto;
import stdev.domain.user.presentation.dto.req.UserLoginDto;
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



}