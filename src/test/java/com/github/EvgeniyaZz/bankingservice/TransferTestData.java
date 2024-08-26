package com.github.EvgeniyaZz.bankingservice;

import com.github.EvgeniyaZz.bankingservice.model.Transfer;
import com.github.EvgeniyaZz.bankingservice.to.TransferTo;
import com.github.EvgeniyaZz.bankingservice.web.MatcherFactory;

public class TransferTestData {
    public static final MatcherFactory.Matcher<Transfer> TRANSFER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Transfer.class,
            "registered", "accountFrom", "accountTo");

    public static final TransferTo transferTo = new TransferTo(null, 100, 2);
}
