package com.github.EvgeniyaZz.bankingservice;

import com.github.EvgeniyaZz.bankingservice.model.UserDetail;
import com.github.EvgeniyaZz.bankingservice.to.UserDetailTo;
import com.github.EvgeniyaZz.bankingservice.web.MatcherFactory;

import java.time.LocalDate;

public class UserDetailTestData {

    public static final MatcherFactory.Matcher<UserDetail> USER_DETAIL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(UserDetail.class, "user");

    public static final UserDetailTo userDetailTo = new UserDetailTo(null, "Смирнова", "Юлия",
            "Андреевна", LocalDate.of(2000, 10, 3));

    public static final UserDetail userDetail1 = new UserDetail(1, "Захарова", "Евгения",
            "Владимировна", LocalDate.of(1992, 2, 19));
}
