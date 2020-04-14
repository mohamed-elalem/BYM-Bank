package com.bym.bankingsystem.utils.account.exchangers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.repositories.TransactionRepository;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DepositExchanger implements Exchanger {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public DepositExchanger(AccountRepository accountRepository,
                            TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account exchange(Account account, TransactionType transactionType, Float amount) {
        Transaction transaction = Transaction.create()
                .withAmount(amount)
                .withTransactionDate(LocalDate.now())
                .withAccountFrom(account)
                .withTransactionType(transactionType)
                .build();

        transactionRepository.save(transaction);

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        return account;
    }
}
