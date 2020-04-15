package com.bym.bankingsystem.models.account;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class AccountType {
    public static class AccountTypeBuilder implements Builder<AccountType> {
        private AccountType accountType;

        public AccountTypeBuilder() {
            this.accountType = new AccountType();
        }

        public AccountType.AccountTypeBuilder withName(String name){
            accountType.setName(name);
            return this;
        }

        public AccountType.AccountTypeBuilder withAccounts(List<Account> accounts){
            accountType.setAccounts(accounts);
            return this;
        }

        @Override
        public AccountType build() {
            return accountType;
        }
    }


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static AccountType.AccountTypeBuilder create() {
        return new AccountType.AccountTypeBuilder();
    }

}
