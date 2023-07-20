package com.dansmultipro.test.jobwebservices.service.impl;

import com.dansmultipro.test.jobwebservices.entity.LoginAbleUser;
import com.dansmultipro.test.jobwebservices.entity.User;
import com.dansmultipro.test.jobwebservices.model.request.AuthRequest;
import com.dansmultipro.test.jobwebservices.model.response.AuthResponse;
import com.dansmultipro.test.jobwebservices.repository.UserRepository;
import com.dansmultipro.test.jobwebservices.security.JWTUtil;
import com.dansmultipro.test.jobwebservices.security.PBKDF2Encoder;
import com.dansmultipro.test.jobwebservices.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PBKDF2Encoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Override
    public AuthResponse authLogin(AuthRequest authRequest) {
        Optional<User> user;
        if (authRequest.getUsername() == null || authRequest.getUsername().equalsIgnoreCase(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username can not be null or empty.");
        user = userRepository.findByUsername(authRequest.getUsername());
        if (!user.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found.");

        LoginAbleUser loginAbleUser = user.get();
        CharSequence password = loginAbleUser.getPassword();
        String passwordRequest = passwordEncoder.encode(authRequest.getPassword());
        if (!password.equals(passwordRequest))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password does not match.");

        return AuthResponse.builder()
                .username(loginAbleUser.getUsername())
                .nickname(loginAbleUser.getNickname())
                .token(jwtUtil.generateToken(loginAbleUser))
                .build();
    }

}
