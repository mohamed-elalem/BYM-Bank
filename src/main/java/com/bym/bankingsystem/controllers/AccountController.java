package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.services.IAccountService;
import com.bym.bankingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/accounts")
public class AccountController {
    IAccountService iAccountService;
    UserService userService;

    public  AccountController(IAccountService iAccountService,UserService userService){
        this.iAccountService = iAccountService;
        this.userService = userService;
    }
    @PostMapping(value = "")
    public ResponseEntity createAccount(@RequestBody @Valid Account account,Long userId, BindingResult bindingResult){

        try {
          Optional<User> user=  userService.findByIdAndRole ( userId,"ROLE_USER" );
          if(user.isPresent ()){
              Account acc = this.iAccountService.save(account,user.get ());
              return  ResponseEntity.ok(acc.getId ());
          }else{
              throw new IllegalArgumentException ( );
          }
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( bindingResult.getFieldError ());
        }
    }

    @GetMapping(value ="")
    public ResponseEntity<?> findAll(){
        try {
            List<Account> accounts = this.iAccountService.getAllAccounts();
            return  ResponseEntity.ok(accounts);
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( null );
        }

    }

    @GetMapping(value = "{id}")
    public ResponseEntity getById(@PathVariable("id")  Long id){

        Optional<Account> acc = this.iAccountService.getSingleAccount(id);
            if(acc.isPresent ()){
                return  ResponseEntity.ok(acc);
            }else{
                return ResponseEntity.notFound ().build ();
            }
    }

    @GetMapping(value = "get-by-account-number/{accountNumber}")
    public ResponseEntity getById(@PathVariable("accountNumber")  String accountNumber){
        try {
            Account acc = this.iAccountService.getByAccountNumber (accountNumber);
            return  ResponseEntity.ok(acc);
        }catch (Exception ex){
            return ResponseEntity.badRequest ().body ( ex.getMessage ());
        }
    }



}


