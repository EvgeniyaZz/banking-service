package com.github.EvgeniyaZz.bankingservice.service;

import com.github.EvgeniyaZz.bankingservice.model.*;
import com.github.EvgeniyaZz.bankingservice.repository.*;
import com.github.EvgeniyaZz.bankingservice.to.UserTo;
import com.github.EvgeniyaZz.bankingservice.util.exception.IllegalRequestDataException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.EvgeniyaZz.bankingservice.config.LoginSecurityConfiguration.PASSWORD_ENCODER;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final MailRepository mailRepository;
    private final AccountRepository accountRepository;
    private final EntityManager em;


    @Transactional
    public User save(UserTo userTo) {
        User user = userRepository.save(new User(null, userTo.getLogin(), PASSWORD_ENCODER.encode(userTo.getPassword()), Role.USER));
        accountRepository.save(new Account(userTo.getAmount(), user));
        mailRepository.save(new Mail(userTo.getEmail().toLowerCase(), user));
        phoneRepository.save(new Phone(userTo.getNumber(), user));
        return user;
    }

    public User getByNumber(String number) {
        Phone phone = phoneRepository.getByNumber(number);
        return em.find(User.class, phone.getUser().getId());
    }

    public User getByEmail(String email) {
        Mail mail = mailRepository.getByEmail(email);
        return em.find(User.class, mail.getUser().getId());
    }

    public List<User> getByName(String name) {
        if (name != null) {
            String search = name.replaceAll(",", " ").replaceAll("\\s+", " ").trim().toLowerCase();
            return userRepository.filterByName(search);
        }
        throw new IllegalRequestDataException("invalid request");
    }
}
