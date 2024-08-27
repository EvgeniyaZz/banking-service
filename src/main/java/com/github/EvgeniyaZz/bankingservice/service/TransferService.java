package com.github.EvgeniyaZz.bankingservice.service;

import com.github.EvgeniyaZz.bankingservice.model.Account;
import com.github.EvgeniyaZz.bankingservice.model.Transfer;
import com.github.EvgeniyaZz.bankingservice.repository.AccountRepository;
import com.github.EvgeniyaZz.bankingservice.repository.TransferRepository;
import com.github.EvgeniyaZz.bankingservice.to.TransferTo;
import com.github.EvgeniyaZz.bankingservice.util.exception.DataConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Transfer doTransfer(TransferTo transferTo, Account accountFrom){
        int amount = transferTo.getAmount();
        if(amount > 0 && amount <= accountFrom.getAmount()) {  // проверяем что денег достаточно на счете
            Account accountTo = accountRepository.getExisted(transferTo.getAccountToId()); //получаем счет куда переводим деньги

            Transfer transfer = transferRepository.save(new Transfer(amount, accountFrom, accountTo)); // сохраняем информацию о переводе денег

            accountFrom.setAmount(accountFrom.getAmount() - amount);
            accountRepository.save(accountFrom); //списываем деньги

            accountTo.setAmount(accountTo.getAmount() + amount);
            accountRepository.save(accountTo); //зачисляем деньги

            return transfer;
        } else {
            throw new DataConflictException("Invalid amount");
        }
    }
}
