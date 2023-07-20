package com.dansmultipro.test.jobwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseResponse <T> {

    private int code;
    private String status;
    private T data;

}
