package com.github.EvgeniyaZz.bankingservice.util;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDetailUtil {

    public static UserDetail createNewFromTo(UserDetailTo userDetailTo, User user) {
        return new UserDetail(userDetailTo.getLastname(), userDetailTo.getFirstname(), userDetailTo.getMiddlename(),
                userDetailTo.getBirthDate(), user);
    }
}
