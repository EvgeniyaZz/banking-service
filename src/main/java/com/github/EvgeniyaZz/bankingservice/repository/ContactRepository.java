package com.github.EvgeniyaZz.bankingservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@NoRepositoryBean
public interface ContactRepository<T> extends BaseRepository<T> {

    @Query("SELECT COUNT (e.id) FROM #{#entityName} e WHERE e.user.id=:userId")
    int countByUserId(int userId);

    default void checkLast(int userId) {
        if (countByUserId(userId) < 2) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "can not delete the last number");
        }
    }
}
