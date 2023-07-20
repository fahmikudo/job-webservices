package com.dansmultipro.test.jobwebservices.controller;

import com.dansmultipro.test.jobwebservices.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<BaseResponse<?>> defaultErrorHandler(HttpServletRequest req, Exception e) {
        BaseResponse<?> baseResponse = BaseResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .data(e.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<BaseResponse<?>> restException(HttpServletRequest req, ResponseStatusException e) {
        BaseResponse<?> baseResponse = BaseResponse.builder()
                .code(e.getStatus().value())
                .status(e.getStatus().name())
                .data(e.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(baseResponse, e.getStatus());
    }

}
