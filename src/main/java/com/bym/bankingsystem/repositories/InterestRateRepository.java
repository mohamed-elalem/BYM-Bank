package com.bym.bankingsystem.repositories;
import com.bym.bankingsystem.models.setting.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate,Long> {
}
