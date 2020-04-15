package com.bym.bankingsystem.repositories;

import com.bym.bankingsystem.models.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    @Query(value="SELECT * FROM account a WHERE a.account_number =?1", nativeQuery = true)
    public  Account getAccountByNumber(String accountNumber);

    @Query(value = "SELECT a from Account a JOIN a.user u where a.id = :accountId AND u.id = :userId AND a.active = true")
    public Optional<Account> getAccountByUser(@Param("userId") Long userId, @Param("accountId") Long accountId);

    @Query(value="SELECT a FROM InterestRate i left join i.accounts a")
    public Page<Account> getAccountWithInterestRate(PageRequest pageRequest);
}
