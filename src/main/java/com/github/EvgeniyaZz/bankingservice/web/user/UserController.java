package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.service.UserService;
import com.github.EvgeniyaZz.bankingservice.web.AbstractController;
import com.github.EvgeniyaZz.bankingservice.web.JwtUser;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController {
    static final String REST_URL = "/api/users";

    UserService service;

    @GetMapping("/filter-by-date")
    public List<User> filterByDate(@RequestParam @Nullable LocalDate date, JwtUser jwtUser) {
        log.info("filter by date {}", date);
        findByJwtUser(jwtUser);
        return repository.filterByDate(date);
    }

    @GetMapping("/by-number")
    public User getByNumber(@RequestParam @Nullable String number, JwtUser jwtUser) {
        log.info("get by number {}", number);
        findByJwtUser(jwtUser);
        return service.getByNumber(number);
    }

    @GetMapping("/by-email")
    public User getByEmail(@RequestParam @Nullable String email, JwtUser jwtUser) {
        log.info("get by email {}", email);
        findByJwtUser(jwtUser);
        return service.getByEmail(email);
    }

    @GetMapping("/by-name")
    public List<User> getByName(@RequestParam @Nullable String name, JwtUser jwtUser) {
        log.info("get by name {}", name);
        findByJwtUser(jwtUser);
        return service.getByName(name);
    }
}
