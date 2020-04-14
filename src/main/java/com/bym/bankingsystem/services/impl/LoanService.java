package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.models.loan.Loan;
import com.bym.bankingsystem.repositories.ILoanRepository;
import com.bym.bankingsystem.services.ILoanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService implements ILoanService {
   private ILoanRepository iLoanRepository;
    public LoanService(ILoanRepository iLoanRepository){
        this.iLoanRepository = iLoanRepository;
    }
    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = this.iLoanRepository.findAll ();
        return loans;
    }

    @Override
    public Optional<Loan> getSingleLoan(Long id) {
        Optional<Loan> loan = this.iLoanRepository.findById ( id );
        return loan;
    }

    @Override
    public Loan save(Loan loan) {
        Loan ln = this.iLoanRepository.save ( loan );
        return ln;
    }
}
