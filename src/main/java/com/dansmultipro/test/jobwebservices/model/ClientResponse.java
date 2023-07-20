package com.dansmultipro.test.jobwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientResponse <T> {

    private HttpStatus httpStatus;
    private T content;

}
