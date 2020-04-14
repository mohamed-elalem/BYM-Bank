package com.bym.bankingsystem.models.setting;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class InterestRate {
    public static class InterestRateBuilder implements Builder<InterestRate> {
        private InterestRate interestRate;

        public InterestRateBuilder() {
            this.interestRate = new InterestRate();
        }

        public InterestRate.InterestRateBuilder withId(Long id) {
            interestRate.setId(id);
            return this;
        }

        public InterestRate.InterestRateBuilder withName(String name){
            interestRate.setName(name);
            return this;
        }

        public InterestRate.InterestRateBuilder withRate(Double rate){
            interestRate.setRate(rate);
            return this;
        }

        @Override
        public InterestRate build() {
            return interestRate;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private Double rate;

    @JsonIgnore
    @OneToMany(mappedBy = "interestRate")
    private List<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public static InterestRate.InterestRateBuilder create() {
        return new InterestRate.InterestRateBuilder();
    }
}
