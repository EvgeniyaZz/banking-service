package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;

import static com.github.EvgeniyaZz.bankingservice.util.UserDetailUtil.createNewFromTo;

public interface UserDetailRepository extends BaseRepository<UserDetail> {

    default void saveNotExisted(UserDetailTo userDetailTo, User user) {
        save(createNewFromTo(userDetailTo, user));
    }
}
