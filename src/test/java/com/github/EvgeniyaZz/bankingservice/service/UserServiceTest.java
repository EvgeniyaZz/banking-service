package com.github.EvgeniyaZz.bankingservice.service;

import com.github.EvgeniyaZz.bankingservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.EvgeniyaZz.bankingservice.UserTestData.user;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.USER_MATCHER;

class UserServiceTest extends AbstractServiceTest{

    @Autowired
    UserService service;

    @Test
    void getByNumber() {
        User user1 = service.getByNumber("79500024743");
        USER_MATCHER.assertMatch(user1, user);
    }
}