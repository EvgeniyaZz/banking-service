package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.repository.UserDetailRepository;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.github.EvgeniyaZz.bankingservice.util.ValidationUtil.assureIdConsistent;

@AllArgsConstructor
@RestController
@RequestMapping(value = DetailController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DetailController extends AbstractController {
    static final String REST_URL = "/api/profile/detail";

    private final UserDetailRepository userDetailRepository;

    @GetMapping
    public UserDetail get(JwtUser jwtUser) {
        log.info("get detail for user {}", jwtUser);
        return userDetailRepository.getExisted(jwtUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void save(@RequestBody @Valid UserDetailTo userDetailTo, JwtUser jwtUser) {
        log.info("save user detail {}", userDetailTo);
        assureIdConsistent(userDetailTo, jwtUser.id());
        User user = findByJwtUser(jwtUser);
        userDetailRepository.saveNotExisted(userDetailTo, user);
    }
}
