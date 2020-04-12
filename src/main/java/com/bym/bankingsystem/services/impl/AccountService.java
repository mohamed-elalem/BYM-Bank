package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account getSingleAccount(Long id) {
        return (Account) this.accountRepository.findById ( id ).get ();
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return this.accountRepository.getAccountByNumber(accountNumber);
    }

    @Override
    public Account save(Account account) {
        Account ac = this.accountRepository.save(account);
        return ac;
    }

    @Override
    public void delete(Long id) {
        this.accountRepository.deleteById(id);
    }
}
