package com.bym.bankingsystem.seeds;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.account.AccountType;
import com.bym.bankingsystem.models.auth.Privilege;
import com.bym.bankingsystem.models.auth.Role;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.loan.Loan;
import com.bym.bankingsystem.models.setting.InterestRate;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationSeeds {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PrivilegeRepository privilegeRepository;

    private PasswordEncoder passwordEncoder;

    private TransactionTypeRepository transactionTypeRepository;

    private AccountRepository accountRepository;

    private AccountTypeRepository accountTypeRepository;

    private InterestRateRepository interestRateRepository;

    private ILoanRepository iLoanRepository;

    public ApplicationSeeds(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PrivilegeRepository privilegeRepository,
            PasswordEncoder passwordEncoder,
            TransactionTypeRepository transactionTypeRepository,
            AccountTypeRepository accountTypeRepository,
            AccountRepository accountRepository,
            InterestRateRepository interestRateRepository,
            ILoanRepository iLoanRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.transactionTypeRepository = transactionTypeRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountRepository = accountRepository;
        this.interestRateRepository = interestRateRepository;
        this.iLoanRepository = iLoanRepository;
    }

    public void init(){
        // privileges init
        Privilege readPrivilege = Privilege.create().withName("READ_PRIVILEGE").build();
        privilegeRepository.save(readPrivilege);

        Privilege writePrivilege = Privilege.create().withName("WRITE_PRIVILEGE").build();
        privilegeRepository.save(writePrivilege);

        // privileges list for admin and teller
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);

        //admin, user, teller roles init
        Role adminRole = Role.create().withName("ROLE_ADMIN").withPrivileges(adminPrivileges).build();
        roleRepository.save(adminRole);

        Role customerRole = Role.create().withName("ROLE_USER").withPrivileges(Arrays.asList(readPrivilege)).build();
        roleRepository.save(customerRole);

        Role tellerRole = Role.create().withName("ROLE_TELLER").withPrivileges(adminPrivileges).build();
        roleRepository.save(tellerRole);

        // admin init
        User admin = User.create()
                .withEmail("mohamed@test.com")
                .withPassword(this.passwordEncoder.encode("mohamed"))
                .withRoles(Arrays.asList(adminRole))
                .withUsername("mohamed")
                .withEnabled(true)
                .build();
        userRepository.save(admin);

        // teller init
        User teller = User.create()
                .withEmail("yasin@test.com")
                .withPassword(this.passwordEncoder.encode("yasin"))
                .withRoles(Arrays.asList(tellerRole))
                .withUsername("yasin")
                .withEnabled(true)
                .build();
        userRepository.save(teller);

        // customer init
        User customer = User.create()
                .withEmail("bereket@test.com")
                .withPassword(this.passwordEncoder.encode("bereket"))
                .withRoles(Arrays.asList(customerRole))
                .withUsername("bereket")
                .withEnabled(true)
                .build();
        userRepository.save(customer);

        // init account types
        AccountType  checkingType = AccountType.create()
                .withName("CHECKING")
                .build();
        accountTypeRepository.save(checkingType);

        AccountType  savingType = AccountType.create()
                .withName("SAVING")
                .build();
        accountTypeRepository.save(savingType);

        // interest rate init
        InterestRate savingInterestRate = InterestRate.create()
                .withName("SAVING")
                .withRate(0.05)
                .build();
        interestRateRepository.save(savingInterestRate);

        // bank account
        Account bankAccount = Account.create()
                .withUser(admin)
                .withAccountNumber("BANK")
                .withBalance(1000000f)
                .withAccountType(savingType)
                .build();
        accountRepository.save(bankAccount);

        // customer account
        Account customerAccount = Account.create()
                .withUser(customer)
                .withAccountNumber("0001")
                .withBalance(1000f)
                .withInterestRate(savingInterestRate)
                .withAccountType(savingType)
                .build();
        accountRepository.save(customerAccount);

        // loan init
        Loan loan = Loan.create()
                .withLoanApplicationNumber("0001")
                .withLoanAmount(2000)
                .withCurrentPaid(0)
                .withMonths(12)
                .withAccount(customerAccount)
                .withInterestRate(0.08)
                .withStartDate( LocalDate.of(2020,4,1))
                .withLastPaidDate(LocalDate.of(2020,4,1))
                .build();
        iLoanRepository.save(loan);

        // transaction type init
        TransactionType depositType = TransactionType.create()
                .withName("DEPOSIT")
                .build();
        transactionTypeRepository.save(depositType);

        TransactionType withdrawType = TransactionType.create()
                .withName("WITHDRAW")
                .build();
        transactionTypeRepository.save(withdrawType);
    }
}
