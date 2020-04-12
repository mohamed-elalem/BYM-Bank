package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;

    @PostMapping(value = "creataccount")
    public ResponseEntity createAccount(@RequestBody @Valid Account account, BindingResult bindingResult){
        System.out.println ("posting");
        try {
            Account acc = this.iAccountService.save(account);
            return  ResponseEntity.ok(acc.getId ());
        }catch (Exception ex){
            System.out.println ("yyyyyy " +bindingResult.getFieldError ());
            return ResponseEntity.badRequest ().body ( bindingResult.getFieldError ());
        }
    }

    @GetMapping(value ="getallaccounts")
    public ResponseEntity<?> findAll(){
        try {
            List<Account> accounts = this.iAccountService.getAllAccounts();
            return  ResponseEntity.ok(accounts);
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( null );
        }

    }

    @GetMapping(value = "getaccountbyid/{id}")
    public ResponseEntity getById(@PathVariable("id")  Long id){
        try {
            Account acc = this.iAccountService.getSingleAccount(id);
            return  ResponseEntity.ok(acc);
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( ex.getMessage ());
        }
    }

    @GetMapping(value = "getbyaccountnumber/{accountNumber}")
    public ResponseEntity getById(@PathVariable("accountNumber")  String accountNumber){
        try {
            Account acc = this.iAccountService.getByAccountNumber (accountNumber);
            return  ResponseEntity.ok(acc);
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( ex.getMessage ());
        }
    }



}


