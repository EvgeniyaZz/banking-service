package com.github.EvgeniyaZz.bankingservice.config;

import com.github.EvgeniyaZz.bankingservice.model.User;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthorizedUser(@NonNull User user) {
        super(user.getLogin(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    @Override
    public String toString() {
        return "AuthorizedUser:" + id() + '[' + user.getLogin() + ']';
    }
}