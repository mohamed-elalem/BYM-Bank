package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.account.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> getAllAccounts();
    public Account getSingleAccount(Long id);
    public Account save(Account account);
    public void delete(Long id);
}
