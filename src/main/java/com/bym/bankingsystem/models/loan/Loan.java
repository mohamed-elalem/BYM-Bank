package com.bym.bankingsystem.models.loan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loan {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
