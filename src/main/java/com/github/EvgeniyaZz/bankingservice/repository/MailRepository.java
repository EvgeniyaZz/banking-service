package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.model.Mail;
import com.github.EvgeniyaZz.bankingservice.util.exception.IllegalRequestDataException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MailRepository extends ContactRepository<Mail> {

    @Query("SELECT m FROM Mail m WHERE m.email = LOWER(:email)")
    Optional<Mail> findByEmailIgnoreCase(String email);

    @Transactional
    default Mail saveNotExist(Mail mail) {
        if (findByEmailIgnoreCase(mail.getEmail()).isPresent()) {
            throw new IllegalRequestDataException("This email already exists");
        }
        return save(mail);
    }
}
