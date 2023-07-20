package com.dansmultipro.test.jobwebservices.handler;

import com.dansmultipro.test.jobwebservices.model.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseResponse<String> baseResponse = BaseResponse.<String>builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .status(HttpStatus.UNAUTHORIZED.name())
                        .data(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .build();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}
