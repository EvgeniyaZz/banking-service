package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.web.AbstractController;
import com.github.EvgeniyaZz.bankingservice.web.JwtUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractController {
    static final String REST_URL = "/api/profile";

    @GetMapping
    public User get(JwtUser jwtUser) {
        log.info("get {}", jwtUser);
        return findByJwtUser(jwtUser);
    }
}
