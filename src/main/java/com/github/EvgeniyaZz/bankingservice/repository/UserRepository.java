package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.util.exception.NotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u JOIN FETCH u.userDetail WHERE u.id = :id")
    Optional<User> findWithDetail(int id);

    @Query("SELECT u FROM User u WHERE u.login = :login")
    Optional<User> findByLogin(String login);

    default User getExistedByLogin(String login) {
        return findByLogin(login).orElseThrow(() -> new NotFoundException("User with login=" + login + " not found"));
    }
}
