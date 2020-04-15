package com.bym.bankingsystem.repositories;
import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.Transaction;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
