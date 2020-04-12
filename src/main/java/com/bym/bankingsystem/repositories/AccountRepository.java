package com.bym.bankingsystem.repositories;

import com.bym.bankingsystem.models.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    @Query(value="SELECT * FROM account a WHERE a.account_number =?1", nativeQuery = true)
    public  Account getAccountByNumber(String accountNumber);
}
