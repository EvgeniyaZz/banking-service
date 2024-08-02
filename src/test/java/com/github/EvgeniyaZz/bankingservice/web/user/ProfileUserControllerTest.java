package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.repository.UserRepository;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import com.github.EvgeniyaZz.bankingservice.web.AbstractControllerTest;
import com.github.EvgeniyaZz.bankingservice.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.github.EvgeniyaZz.bankingservice.web.user.ProfileUserController.REST_URL;
import static com.github.EvgeniyaZz.bankingservice.web.user.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileUserControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void get() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL), user)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));
    }

    @Test
    void saveUserDetail() throws Exception {
        UserDetailTo userDetailTo = new UserDetailTo(null, "Смирнова", "Юлия", "Андреевна",
                LocalDate.of(2000, 10, 3));

        performJwt(MockMvcRequestBuilders.put(REST_URL + "/detail").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(userDetailTo)), userWithoutDetail)
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}