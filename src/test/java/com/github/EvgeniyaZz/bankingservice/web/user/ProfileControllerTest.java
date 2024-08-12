package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.EvgeniyaZz.bankingservice.web.user.ProfileController.REST_URL;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.USER_MATCHER;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileControllerTest extends AbstractControllerTest {

    @Test
    void get() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL), user)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));
    }
}