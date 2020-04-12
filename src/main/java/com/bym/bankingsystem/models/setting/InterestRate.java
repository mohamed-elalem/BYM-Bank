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

    private Double loanRate;
    private Double savingRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Double loanRate) {
        this.loanRate = loanRate;
    }

    public Double getSavingRate() {
        return savingRate;
    }

    public void setSavingRate(Double savingRate) {
        this.savingRate = savingRate;
    }
}
