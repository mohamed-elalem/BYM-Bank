package com.bym.bankingsystem.models.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountType {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
