package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.loan.Loan;
import java.util.List;
import java.util.Optional;

public interface ILoanService {
    public List<Loan> getAllLoans();
    public Optional<Loan> getSingleLoan(Long id);
    public Loan save(Loan loan);
}
