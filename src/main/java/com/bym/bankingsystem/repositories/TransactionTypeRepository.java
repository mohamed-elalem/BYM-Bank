package com.bym.bankingsystem.repositories;
import com.bym.bankingsystem.models.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType,Long> {
    Optional<TransactionType> findByName(String name);
}
