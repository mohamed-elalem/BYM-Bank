package com.bym.bankingsystem.models.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionType {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
