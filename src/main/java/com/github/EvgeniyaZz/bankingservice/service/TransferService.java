package com.github.EvgeniyaZz.bankingservice.service;

import com.github.EvgeniyaZz.bankingservice.model.Transfer;
import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.repository.TransferRepository;
import com.github.EvgeniyaZz.bankingservice.to.TransferTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    // TODO
    public Transfer doTransfer(TransferTo transferTo, User user) {
        return null;
    }
}
