package com.ineri.ineri_lk.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Kozlov Alexander
 * @version 1.0
 */

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}