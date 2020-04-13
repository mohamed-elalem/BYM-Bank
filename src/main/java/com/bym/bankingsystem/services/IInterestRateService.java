package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.setting.InterestRate;
import com.bym.bankingsystem.models.transaction.TransactionType;

import java.util.List;
import java.util.Optional;

public interface IInterestRateService {
    public List<InterestRate> getAllInterestRates();
    public Optional<InterestRate> getSingleInterestRate(Long id);
    public InterestRate save(InterestRate interestRate);
    public InterestRate update(InterestRate interestRate);
    public void delete(Long id);
}
