package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.util.exception.NotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u JOIN FETCH u.userDetail WHERE u.id = :id")
    Optional<User> findWithDetail(int id);

    @Query("SELECT u FROM User u WHERE u.login = :login")
    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.userDetail.birthDate > :date")
    List<User> filterByDate(LocalDate date);

    @Query("SELECT u FROM User u WHERE u.userDetail.lastname LIKE :name% OR u.userDetail.firstname LIKE :name% OR u.userDetail.middlename LIKE :name%")
    List<User> filterByName(String name);

    @Query("SELECT u FROM User u WHERE u.userDetail.lastname LIKE :lastname% AND u.userDetail.firstname LIKE :firstname%")
    List<User> filterByName(String lastname, String firstname);

    @Query("SELECT u FROM User u WHERE u.userDetail.lastname LIKE :lastname% AND u.userDetail.firstname LIKE :firstname% AND u.userDetail.middlename LIKE :middlename%")
    List<User> filterByName(String lastname, String firstname, String middlename);

    default User getExistedByLogin(String login) {
        return findByLogin(login).orElseThrow(() -> new NotFoundException("User with login=" + login + " not found"));
    }
}
