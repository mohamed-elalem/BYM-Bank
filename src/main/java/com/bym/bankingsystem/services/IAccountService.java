package com.bym.bankingsystem.services;

import com.bym.bankingsystem.exceptions.NotEnoughBalanceForWithdrawException;
import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public List<Account> getAllAccounts();
    public Optional<Account> getSingleAccount(Long id);
    public Account getByAccountNumber(String accountNumber);
    public Account save(Account account, User user);
    public void delete(Long id);
    Optional<Account> getAccountByUser(Long userId, Long accountId);
    Account deposit(Long userId, Long accountId, Float amount) throws Exception;
    Account withdraw(Long userId, Long accountId, Float amount) throws Exception;
}
