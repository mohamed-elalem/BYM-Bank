package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.exceptions.AccountNotFoundException;
import com.bym.bankingsystem.exceptions.NotEnoughBalanceForWithdrawException;
import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.repositories.TransactionRepository;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import com.bym.bankingsystem.repositories.UserRepository;
import com.bym.bankingsystem.services.IAccountService;
import com.bym.bankingsystem.utils.account.exchangers.AccountExchanger;
import com.bym.bankingsystem.utils.account.exchangers.DepositExchanger;
import com.bym.bankingsystem.utils.account.exchangers.Exchanger;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private TransactionRepository transactionRepository;
    private AccountExchanger accountExchanger;
    private Exchanger depositExchanger;
    private Exchanger withdrawExchanger;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository,
                          TransactionTypeRepository transactionTypeRepository,
                          TransactionRepository transactionRepository,
                          AccountExchanger accountExchanger,
                          Exchanger depositExchanger,
                          Exchanger withdrawExchanger) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionRepository = transactionRepository;
        this.depositExchanger = depositExchanger;
        this.withdrawExchanger = withdrawExchanger;
        this.accountExchanger = accountExchanger;
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public Optional<Account> getSingleAccount(Long id) {
        return this.accountRepository.findById( id );
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return this.accountRepository.getAccountByNumber(accountNumber);
    }

    @Override
    public Account save(Account account,User user) {
        account.setUser ( user );
        Account ac = this.accountRepository.save(account);
        return ac;
    }

    @Override
    public void delete(Long id) {
        this.accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> getAccountByUser(Long userId, Long accountId) {
        return accountRepository.getAccountByUser(userId, accountId);
    }

    @Override
    public Account deposit(Long userId, Long accountId, Float amount) throws Exception {
        Optional<Account> account = getAccountByUser(userId, accountId);

        if (account.isPresent()) {
            Optional<TransactionType> transactionType = transactionTypeRepository.findByName("DEPOSIT");
            accountExchanger.setExchanger(depositExchanger);

            return accountExchanger.exchange(account.get(), transactionType.get(), amount);
        }

        throw new AccountNotFoundException();
    }

    @Override
    public Account withdraw(Long userId, Long accountId, Float amount) throws Exception {
        Optional<Account> account = getAccountByUser(userId, accountId);

        if (account.isPresent()) {
            if (account.get().getBalance() < amount) {
                throw new NotEnoughBalanceForWithdrawException();
            }

            Optional<TransactionType> transactionType = transactionTypeRepository.findByName("WITHDRAW");
            accountExchanger.setExchanger(withdrawExchanger);

            return accountExchanger.exchange(account.get(), transactionType.get(), amount);
        }

        throw new AccountNotFoundException();
    }
}
