package com.bym.bankingsystem.repositories;
import com.bym.bankingsystem.models.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType,Long> {
}
