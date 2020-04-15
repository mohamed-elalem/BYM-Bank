package com.bym.bankingsystem.models.loan;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Loan {
    public static class LoanBuilder implements Builder<Loan> {
        private Loan loan;

        public LoanBuilder() {
            this.loan = new Loan();
        }

        public Loan.LoanBuilder withId(Long id){
            loan.setId(id);
            return this;
        }

        public Loan.LoanBuilder withLoanApplicationNumber(String loanApplicationNumber){
            loan.setLoanApplicationNumber(loanApplicationNumber);
            return this;
        }

        public Loan.LoanBuilder withLoanAmount(Float loanAmount){
            loan.setLoanAmount(loanAmount);
            return this;
        }

        public Loan.LoanBuilder withInterestRate(double interestRate){
            loan.setInterestRate(interestRate);
            return this;
        }

        public Loan.LoanBuilder withPaidMonths(int months){
            loan.setPaidMonths(months);
            return this;
        }

        public Loan.LoanBuilder withMonths(int months){
            loan.setMonths(months);
            return this;
        }

        public Loan.LoanBuilder withStartDate(LocalDate loanDate){
            loan.setStartDate(loanDate);
            return this;
        }

        public Loan.LoanBuilder withActive(boolean active){
            loan.setActive(active);
            return this;
        }

        public Loan.LoanBuilder withCurrentPaid(double currentPaid){
            loan.setCurrentPaid(currentPaid);
            return this;
        }

        public Loan.LoanBuilder withLastPaidDate(LocalDate lastPaidDate){
            loan.setLastPaidDate(lastPaidDate);
            return this;
        }

        public Loan.LoanBuilder withAccount(Account account){
            loan.setAccount(account);
            return this;
        }

        @Override
        public Loan build() {
            return loan;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @Column(name = "loan_application_number", nullable = false, unique = true)
    private String loanApplicationNumber;
    @NotNull
    @Column(name = "loan_amount", nullable = false)
    private float loanAmount;
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

    public void setLoanAmount(float loanAmount) {
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

    public static Loan.LoanBuilder create() {
        return new Loan.LoanBuilder();
    }

    public float getNextPayment() {
        float nextPayment = loanAmount / months;
        float paymentWithInterest = nextPayment + (nextPayment * (float)interestRate);
        return paymentWithInterest;
    }
}
