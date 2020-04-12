package com.bym.bankingsystem.models.setting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InterestRate {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
