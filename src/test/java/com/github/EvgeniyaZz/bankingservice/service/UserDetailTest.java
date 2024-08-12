package com.github.EvgeniyaZz.bankingservice.service;

import com.github.EvgeniyaZz.bankingservice.repository.UserDetailRepository;
import com.github.EvgeniyaZz.bankingservice.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.EvgeniyaZz.bankingservice.UserDetailTestData.USER_DETAIL_MATCHER;
import static com.github.EvgeniyaZz.bankingservice.UserDetailTestData.userDetail1;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.NOT_FOUND;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.USER_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDetailTest extends AbstractServiceTest{

    @Autowired
    UserDetailRepository repository;

    @Test
    void getUserDetail() {
        USER_DETAIL_MATCHER.assertMatch(repository.getExisted(USER_ID), userDetail1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> repository.getExisted(NOT_FOUND));
    }
}
