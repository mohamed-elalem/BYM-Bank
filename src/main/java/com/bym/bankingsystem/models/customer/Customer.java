package com.bym.bankingsystem.models.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
