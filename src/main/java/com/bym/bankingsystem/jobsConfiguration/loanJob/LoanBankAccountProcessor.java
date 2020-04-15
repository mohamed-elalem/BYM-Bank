package com.bym.bankingsystem.jobsConfiguration.loanJob;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.repositories.AccountRepository;
import org.springframework.batch.item.ItemProcessor;

public class LoanBankAccountProcessor implements ItemProcessor<Transaction, Transaction> {

    AccountRepository accountRepository;


    LoanBankAccountProcessor( AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction process(Transaction transaction) throws Exception {
        System.out.println(transaction.getAmount());
        Account bankAccount = transaction.getAccountTo();
        bankAccount.setBalance(bankAccount.getBalance() + transaction.getAmount());
        return transaction;
    }
}
