package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.repository.UserDetailRepository;
import com.github.EvgeniyaZz.bankingservice.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.EvgeniyaZz.bankingservice.UserDetailTestData.*;
import static com.github.EvgeniyaZz.bankingservice.UserTestData.*;
import static com.github.EvgeniyaZz.bankingservice.util.UserDetailUtil.createNewFromTo;
import static com.github.EvgeniyaZz.bankingservice.web.UserDetailController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserDetailControllerTest extends AbstractControllerTest {

    @Test
    void get() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL), user)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_DETAIL_MATCHER.contentJson(userDetail1));
    }

    @Test
    void create() throws Exception {
        ResultActions action = performJwt(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(userDetailTo)), userWithoutDetail)
                .andDo(print())
                .andExpect(status().isCreated());

        UserDetail created = USER_DETAIL_MATCHER.readFromJson(action);
        int newId = created.id();
        UserDetail newUserDetail = createNewFromTo(userDetailTo, userWithoutDetail);
        newUserDetail.setId(newId);
        USER_DETAIL_MATCHER.assertMatch(created, newUserDetail);
    }

    @Test
    void changeUserDetail() throws Exception {
        performJwt(MockMvcRequestBuilders.post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(userDetailTo)), user)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}