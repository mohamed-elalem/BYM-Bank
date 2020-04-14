package com.bym.bankingsystem.models.account;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.setting.InterestRate;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Account {

    public static class AccountBuilder implements Builder<Account> {
        private Account account;

        public AccountBuilder() {
            this.account = new Account();
        }

        public Account.AccountBuilder withUser(User user){
            account.setUser(user);
            return this;
        }

        public Account.AccountBuilder withAccountNumber(String accountNumber) {
            account.setAccountNumber(accountNumber);
            return this;
        }

        public Account.AccountBuilder withBalance(Float balance){
            account.setBalance(balance);
            return this;
        }

        public Account.AccountBuilder withActive(boolean active){
            account.setActive(active);
            return this;
        }

       public Account.AccountBuilder withAccountType(AccountType accountType){
           account.setAccountType(accountType);
           return this;
       }

        public Account.AccountBuilder withTransactionsFrom(List<Transaction> transactionsFrom){
            account.setTransactionsFrom(transactionsFrom);
            return this;
        }

        public Account.AccountBuilder withTransactionsTo(List<Transaction> transactionsTo){
            account.setTransactionsTo(transactionsTo);
            return this;
        }

        public Account.AccountBuilder withInterestRate(InterestRate interestRate){
            account.setInterestRate(interestRate);
            return this;
        }

        @Override
        public Account build() {
            return account;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @Column(name = "account_number", nullable = false,unique = true)
    private String accountNumber;

    private Float balance;

    @JsonIgnore
    private boolean active;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "accountFrom")
    @JsonManagedReference
    private List<Transaction> transactionsFrom;

    @OneToMany(mappedBy = "accountTo")
    @JsonManagedReference
    private List<Transaction> transactionsTo;

    @ManyToOne
    private AccountType accountType;

    @ManyToOne
    private InterestRate interestRate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<Transaction> getTransactionsFrom() {
        return transactionsFrom;
    }

    public void setTransactionsFrom(List<Transaction> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    public List<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    public void setTransactionsTo(List<Transaction> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }

    public void addTransactionFrom(Transaction transaction) {
        if (getTransactionsFrom() == null) {
            this.setTransactionsFrom(new ArrayList<>());
        }

        getTransactionsFrom().add(transaction);
    }

    public void addTransactionTo(Transaction transaction) {
        if (getTransactionsTo() == null) {
            this.setTransactionsTo(new ArrayList<>());
        }

        getTransactionsTo().add(transaction);
    }

    public static Account.AccountBuilder create() {
        return new Account.AccountBuilder();
    }

}
