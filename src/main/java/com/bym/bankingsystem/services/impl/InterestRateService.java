package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.models.setting.InterestRate;
import com.bym.bankingsystem.repositories.InterestRateRepository;
import com.bym.bankingsystem.services.IInterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InterestRateService implements IInterestRateService {

    @Autowired
    InterestRateRepository interestRateRepository;

    @Override
    public List<InterestRate> getAllInterestRates() {
        return this.interestRateRepository.findAll();
    }

    @Override
    public InterestRate getSingleInterestRate(Long id) {
        return this.interestRateRepository.getOne(id);
    }

    @Override
    public InterestRate save(InterestRate interestRate) {
        return this.interestRateRepository.save(interestRate);
    }

    @Override
    public InterestRate update(InterestRate interestRate) {
        return this.interestRateRepository.save(interestRate);
    }

    @Override
    public void delete(Long id) {
        //this.interestRateRepository.delete(id);
    }
}
