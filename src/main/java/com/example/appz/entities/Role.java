package com.example.appz.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, CONSULTANT;

    @Override
    public String getAuthority() {
        return name();
    }
}
