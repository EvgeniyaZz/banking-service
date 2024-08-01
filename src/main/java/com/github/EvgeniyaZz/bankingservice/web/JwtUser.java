package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.HasIdAndLogin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class JwtUser extends JwtAuthenticationToken implements HasIdAndLogin {
    @Getter
    @Setter
    private Integer id;

    public JwtUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities, int id) {
        super(jwt, authorities);
        this.id = id;
    }

    @Override
    public String getLogin() {
        return getName();
    }

    @Override
    public String toString() {
        return "JwtUser:" + id + '[' + getName() + ']';
    }
}