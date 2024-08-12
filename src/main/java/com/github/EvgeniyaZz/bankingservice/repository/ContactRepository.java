package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.util.exception.DataConflictException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@NoRepositoryBean
public interface ContactRepository<T> extends BaseRepository<T> {

    @Query("SELECT COUNT (e.id) FROM #{#entityName} e WHERE e.user.id=:userId")
    int countByUserId(int userId);

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id and e.user.id = :userId")
    Optional<T> get(int id, int userId);

    default T getBelonged(int id, int userId) {
        return get(id, userId).orElseThrow(
                () -> new DataConflictException("Entity with id=" + id + " is not exist or doesn't belong to user id=" + userId));
    }

    default void checkLast(int userId) {
        if (countByUserId(userId) < 2) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "can not delete the last");
        }
    }

    @Transactional
    default void deleteNotLast(T entity, int userId) {
        checkLast(userId);
        delete(entity);
    }
}
