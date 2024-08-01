package com.github.EvgeniyaZz.bankingservice.service;

import com.github.EvgeniyaZz.bankingservice.model.*;
import com.github.EvgeniyaZz.bankingservice.repository.AccountRepository;
import com.github.EvgeniyaZz.bankingservice.repository.MailRepository;
import com.github.EvgeniyaZz.bankingservice.repository.PhoneRepository;
import com.github.EvgeniyaZz.bankingservice.repository.UserRepository;
import com.github.EvgeniyaZz.bankingservice.to.UserTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.EvgeniyaZz.bankingservice.config.LoginSecurityConfiguration.PASSWORD_ENCODER;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final MailRepository mailRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public User save(UserTo userTo) {
        Account account = accountRepository.save(new Account(userTo.getAccount()));
        User user = userRepository.save(new User(null, userTo.getLogin(), PASSWORD_ENCODER.encode(userTo.getPassword()), account, Role.USER));
        mailRepository.save(new Mail(userTo.getEmail(), user));
        phoneRepository.save(new Phone(userTo.getNumber(), user));
        return user;
    }
}
