package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;

    @GetMapping(value ="getallaccounts")
    public Response findAll(){
    try {
        List<Account> accounts = this.iAccountService.getAllAccounts();
        return new Response(200,"succeed",accounts);
    }catch (Exception ex){
        return new Response(400, ex.getMessage(), null);
    }
    }

    @PostMapping(value = "creataccount")
    public Response createAccount(@RequestBody Account account){
        try {
            Account acc = this.iAccountService.save(account);
            return new Response(200,"succeed",acc.getId());
        }catch (Exception ex){
            return new Response(400, ex.getMessage(), 0);
        }
    }

    @GetMapping(value = "getbyid/{id}")
    public Response getById(@PathVariable("id") Long id){
        try {
            Account acc = this.iAccountService.getSingleAccount(id);
            return new Response(200,"succeed",null,acc);
        }catch (Exception ex){
            return new Response(400, ex.getMessage(), null);
        }
    }



}


