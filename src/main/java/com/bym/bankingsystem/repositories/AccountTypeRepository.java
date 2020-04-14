package com.bym.bankingsystem.repositories;

import com.bym.bankingsystem.models.account.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository   extends JpaRepository<AccountType,Long> {
}
