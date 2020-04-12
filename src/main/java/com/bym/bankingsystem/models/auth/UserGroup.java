package com.bym.bankingsystem.models.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserGroup {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
