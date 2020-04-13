package com.bym.bankingsystem.models.transaction;

import com.bym.bankingsystem.models.Builder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TransactionType {

    public static class TransactionTypeBuilder implements Builder<TransactionType> {
        private TransactionType transactionType;

        public TransactionTypeBuilder() {
            this.transactionType = new TransactionType();
        }

        public TransactionTypeBuilder withName(String name) {
            this.transactionType.setName(name);
            return this;
        }

        public TransactionType build() {
            return this.transactionType;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "transactionType")
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (getTransactions() == null) {
            setTransactions(new ArrayList<>());
        }

        getTransactions().add(transaction);
    }

    public static TransactionTypeBuilder create() {
        return new TransactionTypeBuilder();
    }
}
