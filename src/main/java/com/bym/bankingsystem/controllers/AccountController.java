package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;

    @GetMapping
    public Response findAll(){
    List<Account> accounts = this.iAccountService.getAllAccounts();
    return new Response(200,"succeed",accounts);
    }
}
