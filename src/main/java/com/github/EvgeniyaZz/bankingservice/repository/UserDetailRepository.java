package com.github.EvgeniyaZz.bankingservice.repository;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import com.github.EvgeniyaZz.bankingservice.util.exception.IllegalRequestDataException;
import org.springframework.transaction.annotation.Transactional;

import static com.github.EvgeniyaZz.bankingservice.util.UserDetailUtil.createNewFromTo;

@Transactional(readOnly = true)
public interface UserDetailRepository extends BaseRepository<UserDetail> {

    @Transactional
    default UserDetail saveNotExisted(UserDetailTo userDetailTo, User user) {
        if(findById(user.getId()).isPresent()) {
            throw new IllegalRequestDataException("User details already exist");
        }
        return save(createNewFromTo(userDetailTo, user));
    }
}
