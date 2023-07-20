package com.dansmultipro.test.jobwebservices.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginAbleUser extends UserDetails {
    public String getNickname();

    public String getUsername();

}
