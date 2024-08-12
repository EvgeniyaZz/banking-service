package com.github.EvgeniyaZz.bankingservice.web;

import com.github.EvgeniyaZz.bankingservice.model.Phone;
import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.repository.PhoneRepository;
import com.github.EvgeniyaZz.bankingservice.to.PhoneTo;
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
@RequestMapping(value = PhoneController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PhoneController extends AbstractController {
    static final String REST_URL = "/api/profile/phone";

    private final PhoneRepository phoneRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Phone> createWithLocation(@RequestBody @Valid PhoneTo phoneTo, JwtUser jwtUser) {
        log.info("create phone {}", phoneTo);
        User user = findByJwtUser(jwtUser);
        Phone created = phoneRepository.saveNotExist(new Phone(phoneTo.getNumber(), user));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody @Valid PhoneTo phoneTo, @PathVariable int id, JwtUser jwtUser) {
        log.info("update phone {} with id={}", phoneTo, id);
        Phone phone = phoneRepository.getBelonged(id, jwtUser.id());
        phone.setNumber(phoneTo.getNumber());
        phoneRepository.saveNotExist(phone);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable int id, JwtUser jwtUser) {
        log.info("delete phone {}", id);
        Phone phone = phoneRepository.getBelonged(id, jwtUser.id());
        phoneRepository.deleteNotLast(phone, jwtUser.id());
    }
}
