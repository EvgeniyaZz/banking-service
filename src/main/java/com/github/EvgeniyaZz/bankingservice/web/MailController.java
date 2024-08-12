package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.Mail;
import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.repository.MailRepository;
import com.github.EvgeniyaZz.bankingservice.to.MailTo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping(value = MailController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MailController extends AbstractController {
    static final String REST_URL = "/api/profile/mail";

    private final MailRepository mailRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mail> createWithLocation(@RequestBody @Valid MailTo mailTo, JwtUser jwtUser) {
        log.info("create mail {}", mailTo);
        User user = findByJwtUser(jwtUser);
        Mail created = mailRepository.saveNotExist(new Mail(mailTo.getEmail(), user));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody @Valid MailTo mailTo, @PathVariable int id, JwtUser jwtUser) {
        log.info("update mail {} with id={}", mailTo, id);
        Mail mail = mailRepository.getBelonged(id, jwtUser.id());
        mail.setEmail(mailTo.getEmail());
        mailRepository.saveNotExist(mail);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable int id, JwtUser jwtUser) {
        log.info("delete mail {}", id);
        Mail mail = mailRepository.getBelonged(id, jwtUser.id());
        mailRepository.deleteNotLast(mail, jwtUser.id());
    }
}
