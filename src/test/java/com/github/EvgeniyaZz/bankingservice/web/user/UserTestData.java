package com.github.EvgeniyaZz.bankingservice.web.user;

import com.github.EvgeniyaZz.bankingservice.model.Role;
import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.web.MatcherFactory;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class,
            "password", "phones", "mails", "account");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int USER2_ID = 3;

    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user@gmail.com";
    public static final String ADMIN_MAIL = "admin@javaops.ru";

    public static final User user = new User(USER_ID, "User", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin", Role.ADMIN, Role.USER);
    public static final User userWithoutDetail = new User(USER2_ID, "User2", "user2", Role.USER);


}
