package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.Transfer;
import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.service.TransferService;
import com.github.EvgeniyaZz.bankingservice.to.TransferTo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping(value = TransferController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferController extends AbstractController {
    static final String REST_URL = "/api/transfer";

    private final TransferService transferService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transfer> doTransfer(@RequestBody @Valid TransferTo transferTo, JwtUser jwtUser) {
        log.info("transfer {}", transferTo);
        User user = findByJwtUser(jwtUser);
        Transfer transfer = transferService.doTransfer(transferTo, user.getAccount());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(transfer.id())
                .toUri();

        return ResponseEntity.created(uriOfNewResource).body(transfer);
    }
}
