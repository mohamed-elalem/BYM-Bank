package com.bym.bankingsystem.models.transaction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class TransactionType {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @Column(name = "transaction_type_name", nullable = false)
    private String transactionTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }
}
