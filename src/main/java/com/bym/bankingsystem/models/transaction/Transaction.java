package com.bym.bankingsystem.models.transaction;

import com.bym.bankingsystem.models.account.AccountType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Transaction {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

}
