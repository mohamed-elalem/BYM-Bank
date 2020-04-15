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

        public TransactionBuilder withAccountFrom(Account account) {
            transaction.setAccountFrom(account);
            return this;
        }

        public TransactionBuilder withAccountTo(Account account) {
            transaction.setAccountTo(account);
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
    private Account accountFrom;

    @ManyToOne
    @JsonBackReference
    private Account accountTo;

    @ManyToOne
    @JsonBackReference
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

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public static TransactionBuilder create() {
        return new TransactionBuilder();
    }
}
