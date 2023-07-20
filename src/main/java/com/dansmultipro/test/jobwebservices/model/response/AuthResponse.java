package com.dansmultipro.test.jobwebservices.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {

    private String token;
    private String username;
    private String nickname;

}
