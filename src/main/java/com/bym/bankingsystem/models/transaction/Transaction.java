package com.bym.bankingsystem.models.transaction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private double amount;
    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;
    @Column(name = "from_account_id", insertable = false, updatable = false)
    private Long fromAccountId;
    @Column(name = "to_account_id", insertable = false, updatable = false)
    private Long toAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }
}
