package com.bym.bankingsystem.repositories;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.loan.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {
    @Query(value="SELECT l FROM Loan l WHERE l.active = true and l.paidMonths < l.months")
    public Page<Loan> getActiveLoans(PageRequest pageRequest);
}
