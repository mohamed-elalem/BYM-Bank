package com.bym.bankingsystem.models.transaction;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Transaction {

    public static class TransactionBuilder implements Builder<Transaction> {
        private Transaction transaction;

        public TransactionBuilder() {
            this.transaction = new Transaction();
        }

        public TransactionBuilder withAmount(Float amount) {
            transaction.setAmount(amount);
            return this;
        }

        public TransactionBuilder withTransactionDate(LocalDate date) {
            transaction.setTransactionDate(date);
            return this;
        }

        public TransactionBuilder withTransactionType(TransactionType transactionType) {
            transaction.setTransactionType(transactionType);
            return this;
        }

        public TransactionBuilder withAccount(Account account) {
            transaction.setAccount(account);
            return this;
        }

        @Override
        public Transaction build() {
            return transaction;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private Float amount;
    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @ManyToOne
    @JsonBackReference
    private Account account;

    @ManyToOne
    private TransactionType transactionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static TransactionBuilder create() {
        return new TransactionBuilder();
    }
}
