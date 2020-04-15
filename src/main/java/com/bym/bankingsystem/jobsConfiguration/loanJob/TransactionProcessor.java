package com.bym.bankingsystem.jobsConfiguration.loanJob;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.util.Optional;

public class TransactionProcessor implements ItemProcessor<Account, Transaction> {

    AccountRepository accountRepository;
    TransactionTypeRepository transactionTypeRepository;

    TransactionProcessor( AccountRepository accountRepository,
                          TransactionTypeRepository transactionTypeRepository){
     this.accountRepository = accountRepository;
     this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Transaction process(Account account) throws Exception {

        Account bankAccount = accountRepository.getAccountByNumber("BANK");
        Optional<TransactionType> transactionType = transactionTypeRepository.findByName("LOAN");

        if(transactionType.isPresent()){
            Transaction transaction = Transaction.create()
                    .withAmount(account.getLoan().getNextPayment())
                    .withAccountFrom(account)
                    .withTransactionDate(LocalDate.now())
                    .withAccountTo(bankAccount)
                    .withTransactionType(transactionType.get())
                    .build();
            return transaction;
        }
        return null;

    }
}
