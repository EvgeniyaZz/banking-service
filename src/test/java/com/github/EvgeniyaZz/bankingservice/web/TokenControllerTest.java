package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.*;

class TokenControllerTest extends AbstractControllerTest {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    void tokenOk() throws Exception {
        String jwt = getJWT(user);
        Jwt decodedJwt = jwtDecoder.decode(jwt);
        Assertions.assertEquals(user.getLogin(), decodedJwt.getSubject());
        JwtUser jwtUser = JwtUtil.createJwtUser(decodedJwt);
        Assertions.assertEquals(user.id(), jwtUser.id());
    }

    @Test
    void tokenUnauthorized() throws Exception {
        getJWT(user.getLogin(), "dummy")
                .andExpect(status().isUnauthorized());
    }
}