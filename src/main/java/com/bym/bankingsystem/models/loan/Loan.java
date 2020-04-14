package com.bym.bankingsystem.models.loan;

import com.bym.bankingsystem.models.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Loan {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @Column(name = "loan_application_number", nullable = false, unique = true)
    private String loanApplicationNumber;
    @NotNull
    @Column(name = "loan_amount", nullable = false)
    private double loanAmount;
    @Column(name = "current_paid", nullable = false)
    private double currentPaid;
    @Column(name = "paid_months", nullable = false)
    private double paidMonths;
    @NotNull
    @Column(name = "interest_rate", nullable = false)
    private double interestRate;
    @NotNull
    @Column(name = "months", nullable = false)
    private int months;
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "last_paid_date", nullable = false)
    private LocalDate lastPaidDate;
    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToOne
    private Account account;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanApplicationNumber() {
        return loanApplicationNumber;
    }

    public void setLoanApplicationNumber(String loanApplicationNumber) {
        this.loanApplicationNumber = loanApplicationNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCurrentPaid() {
        return currentPaid;
    }

    public void setCurrentPaid(double currentPaid) {
        this.currentPaid = currentPaid;
    }

    public LocalDate getLastPaidDate() {
        return lastPaidDate;
    }

    public void setLastPaidDate(LocalDate lastPaidDate) {
        this.lastPaidDate = lastPaidDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getPaidMonths() {
        return paidMonths;
    }

    public void setPaidMonths(double paidMonths) {
        this.paidMonths = paidMonths;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
