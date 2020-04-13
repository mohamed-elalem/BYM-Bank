package com.bym.bankingsystem.models.account;

import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Account {
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

    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Transaction> transactions;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (getTransactions() == null) {
            this.setTransactions(new ArrayList<>());
        }

        getTransactions().add(transaction);
    }
}
