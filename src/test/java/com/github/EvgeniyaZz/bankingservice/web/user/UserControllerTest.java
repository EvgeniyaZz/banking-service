package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.github.EvgeniyaZz.bankingservice.UserTestData.*;
import static com.github.EvgeniyaZz.bankingservice.web.user.UserController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends AbstractControllerTest {

    @Test
    void filterByDate() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL + "/filter-by-date?date=" + FILTER_DATE), user)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(List.of(user, admin)));
    }

    @Test
    void getByNumber() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL + "/by-number?number=" + ADMIN_NUMBER), user)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(admin));
    }

    @Test
    void getByEmail() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL + "/by-email?email=" + USER2_MAIL), user)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(userWithoutDetail));
    }
}