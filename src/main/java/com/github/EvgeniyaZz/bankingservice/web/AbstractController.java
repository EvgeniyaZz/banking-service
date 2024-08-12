package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.repository.UserRepository;
import com.github.EvgeniyaZz.bankingservice.util.exception.IllegalRequestDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserRepository repository;

    protected User findByJwtUser(JwtUser jwtUser) {
        return repository.findById(jwtUser.id()).orElseThrow(
                () -> new IllegalRequestDataException("User id='" + jwtUser.getId() + "' was not found"));
    }
}
