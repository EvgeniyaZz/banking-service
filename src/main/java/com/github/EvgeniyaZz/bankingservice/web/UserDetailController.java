package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.repository.UserDetailRepository;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping(value = UserDetailController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserDetailController extends AbstractController {
    static final String REST_URL = "/api/profile/detail";

    private final UserDetailRepository userDetailRepository;

    @GetMapping
    public UserDetail get(JwtUser jwtUser) {
        log.info("get detail for user {}", jwtUser);
        return userDetailRepository.getExisted(jwtUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<UserDetail> createWithLocation(@RequestBody @Valid UserDetailTo userDetailTo, JwtUser jwtUser) {
        log.info("create user detail {}", userDetailTo);
        User user = findByJwtUser(jwtUser);
        UserDetail created = userDetailRepository.saveNotExisted(userDetailTo, user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
