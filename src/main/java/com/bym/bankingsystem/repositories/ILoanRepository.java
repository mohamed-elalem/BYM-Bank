package com.bym.bankingsystem.repositories;

import com.bym.bankingsystem.models.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {
}
