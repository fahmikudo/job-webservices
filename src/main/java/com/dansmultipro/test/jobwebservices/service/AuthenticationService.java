package com.dansmultipro.test.jobwebservices.service;

import com.dansmultipro.test.jobwebservices.model.request.AuthRequest;
import com.dansmultipro.test.jobwebservices.model.response.AuthResponse;

public interface AuthenticationService {

    AuthResponse authLogin(AuthRequest authRequest);

}
