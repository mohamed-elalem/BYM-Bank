package com.bym.bankingsystem.jobsConfiguration.loanJob;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.loan.Loan;
import org.springframework.batch.item.ItemProcessor;

public class LoanCustomerAccountProcessor implements ItemProcessor<Loan, Account> {

    @Override
    public Account process(Loan loan) throws Exception {
       Account customerAccount = loan.getAccount();
        customerAccount.setBalance(customerAccount.getBalance() - loan.getNextPayment());
        return customerAccount;
    }
}
