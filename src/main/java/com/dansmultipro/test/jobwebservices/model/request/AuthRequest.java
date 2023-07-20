package com.dansmultipro.test.jobwebservices.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {

    private String username;
    private String password;

}
