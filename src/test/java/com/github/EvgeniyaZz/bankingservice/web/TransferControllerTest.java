package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.Transfer;
import com.github.EvgeniyaZz.bankingservice.repository.TransferRepository;
import com.github.EvgeniyaZz.bankingservice.to.TransferTo;
import com.github.EvgeniyaZz.bankingservice.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.EvgeniyaZz.bankingservice.UserTestData.*;
import static com.github.EvgeniyaZz.bankingservice.web.TransferController.REST_URL;
import static com.github.EvgeniyaZz.bankingservice.TransferTestData.TRANSFER_MATCHER;
import static com.github.EvgeniyaZz.bankingservice.TransferTestData.transferTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransferControllerTest extends AbstractControllerTest{

    @Autowired
    TransferRepository repository;

    @Test
    void doTransfer() throws Exception {
        ResultActions action = performJwt(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(transferTo)), user)
                .andDo(print())
                .andExpect(status().isCreated());

        Transfer transfer = TRANSFER_MATCHER.readFromJson(action);
        int newId = transfer.id();
        Transfer newTransfer = new Transfer(transferTo.getAmount());
        newTransfer.setId(newId);
        TRANSFER_MATCHER.assertMatch(transfer, newTransfer);
        TRANSFER_MATCHER.assertMatch(repository.getExisted(newId), newTransfer);
    }

    @Test
    void doTransferUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(transferTo)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void doTransferInvalidAmount() throws Exception {
        TransferTo newTo = new TransferTo(null, 5000, ADMIN_ID);
        performJwt(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)), user)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void doTransferNotFoundAccount() throws Exception {
        TransferTo newTo = new TransferTo(null, 100, NOT_FOUND);
        performJwt(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)), user)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}