package com.bym.bankingsystem.jobsConfiguration.loanJob;

import com.bym.bankingsystem.models.loan.Loan;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;


public class LoanProcessor  implements ItemProcessor<Loan, Loan> {

    @Override
    public Loan process(Loan loan) throws Exception {
        if(loan.getAccount().getBalance() < loan.getNextPayment()){
            return null;
        }


        loan.setPaidMonths(loan.getPaidMonths() + 1);
        loan.setCurrentPaid(loan.getCurrentPaid() + loan.getNextPayment());
        loan.setLastPaidDate(LocalDate.now());
        return loan;
    }
}
