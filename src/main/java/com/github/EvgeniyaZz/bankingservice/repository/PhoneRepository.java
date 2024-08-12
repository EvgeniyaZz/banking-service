package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.model.Phone;
import com.github.EvgeniyaZz.bankingservice.util.exception.IllegalRequestDataException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PhoneRepository extends ContactRepository<Phone> {

    @Query("SELECT p FROM Phone p WHERE p.number = :number")
    Optional<Phone> findByNumber(String number);

    @Transactional
    default Phone saveNotExist(Phone phone) {
        if (findByNumber(phone.getNumber()).isPresent()) {
            throw new IllegalRequestDataException("This phone already exists");
        }
        return save(phone);
    }
}
