package com.dansmultipro.test.jobwebservices.controller;

import com.dansmultipro.test.jobwebservices.model.BaseResponse;
import com.dansmultipro.test.jobwebservices.model.request.AuthRequest;
import com.dansmultipro.test.jobwebservices.model.response.AuthResponse;
import com.dansmultipro.test.jobwebservices.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<AuthResponse>> authLogin(@RequestBody AuthRequest request) {
        AuthResponse response = authenticationServiceImpl.authLogin(request);
        BaseResponse<AuthResponse> authResponseBaseResponse = BaseResponse.<AuthResponse>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(response)
                .build();
        return new ResponseEntity<>(authResponseBaseResponse, HttpStatus.OK);

    }

}
