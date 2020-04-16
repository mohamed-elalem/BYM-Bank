package com.bym.bankingsystem.jobsConfiguration.interestRateJob;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class InterestRateTransactionProcessor implements ItemProcessor<Account, Transaction> {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionTypeRepository transactionTypeRepository;


    @Override
    public Transaction process(Account account) throws Exception {

        Account bankAccount = accountRepository.getAccountByNumber("BANK");
        Optional<TransactionType> transactionType = transactionTypeRepository.findByName("DEPOSIT");
        float balance =  account.getInterestRateValue();

        if(transactionType.isPresent() && balance < bankAccount.getBalance()){
            bankAccount.setBalance(bankAccount.getBalance() - balance);
            account.setBalance(account.getBalance() + balance);
            Transaction transaction = Transaction.create()
                    .withAmount(balance)
                    .withAccountFrom(bankAccount)
                    .withTransactionDate(LocalDate.now())
                    .withAccountTo(account)
                    .withTransactionType(transactionType.get())
                    .build();
            return transaction;
        }
        return null;

    }
}
