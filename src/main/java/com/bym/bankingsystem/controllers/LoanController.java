package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.loan.Loan;
import com.bym.bankingsystem.repositories.ILoanRepository;
import com.bym.bankingsystem.services.IAccountService;
import com.bym.bankingsystem.services.ILoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/teller/loan")
public class LoanController {
    private ILoanService iLoanService;
    private IAccountService iAccountService;
    public  LoanController( ILoanService iLoanService,IAccountService iAccountService){
        this.iLoanService = iLoanService;
        this.iLoanService = iLoanService;
    }

    @PostMapping(value = "createloan/{accountId}")
    public ResponseEntity createAccount(@RequestBody @Valid Loan loan, @PathVariable("accountId") Long accountId, BindingResult bindingResult){

        try {
           Optional<Account> account = this.iAccountService.getSingleLoan ( accountId );
            if(account.isPresent ()){
                loan.setAccount ( account.get () );
              Loan ln =  this.iLoanService.save ( loan );
              return  ResponseEntity.ok (ln);
            }else{
                throw new IllegalArgumentException ( );
            }
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( bindingResult.getFieldError ());
        }
    }

    @PutMapping(value = "activate/{loanId}")
    public ResponseEntity createAccount(@PathVariable("accountId") Long loanId){

        try {
            Optional<Loan> loan = this.iLoanService.getSingleLoan ( loanId );
            if(loan.isPresent ()){
                loan.get ().setActive ( true );
                Loan ln =  this.iLoanService.save ( loan.get () );
                return  ResponseEntity.ok (ln);
            }else{
                throw new IllegalArgumentException ( );
            }
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( bindingResult.getFieldError ());
        }
    }
}
