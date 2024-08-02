package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import com.github.EvgeniyaZz.bankingservice.web.JwtUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.github.EvgeniyaZz.bankingservice.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = ProfileUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileUserController extends AbstractUserController {
    static final String REST_URL = "/api/profile";

    @GetMapping
    public User get(JwtUser jwtUser) {
        log.info("get {}", jwtUser);
        return findByJwtUser(jwtUser);
    }

    @PutMapping(value = "/detail", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void saveUserDetail(@RequestBody @Valid UserDetailTo userDetailTo, JwtUser jwtUser) {
        assureIdConsistent(userDetailTo, jwtUser.id());
        User user = findByJwtUser(jwtUser);
        userDetailRepository.saveNotExisted(userDetailTo, user);
    }
}
