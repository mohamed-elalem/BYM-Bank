package com.bym.bankingsystem.models.customer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Customer {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @NotBlank
    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @NotBlank
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getContactPhone() {
        return contactPhone;
    }
}
