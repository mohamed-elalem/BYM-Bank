package com.bym.bankingsystem.controllers.teller;

import com.bym.bankingsystem.exceptions.AccountNotFoundException;
import com.bym.bankingsystem.exceptions.NotEnoughBalanceForWithdrawException;
import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.services.IAccountService;
import com.bym.bankingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/teller/users/{userId}/accounts")
public class UserAccountController {
    IAccountService iAccountService;
    UserService userService;

    public UserAccountController(IAccountService iAccountService, UserService userService){
        this.iAccountService = iAccountService;
        this.userService = userService;
    }
    @PostMapping(value = "")
    public ResponseEntity createAccount(@RequestBody @Valid Account account, @PathVariable("userId") Long userId, BindingResult bindingResult){

        try {
          Optional<User> user=  userService.findByIdAndRole ( userId,"ROLE_USER" );

          if(user.isPresent ()){
              account.setActive(true);
              Account acc = this.iAccountService.save(account,user.get ());
              return  ResponseEntity.ok(acc);
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

    @PatchMapping("{id}/deposit")
    public ResponseEntity<?> depositAction(@RequestBody Map<String, Float> payload, @PathVariable("userId") Long userId, @PathVariable("id") Long accountId) throws Exception {
        try {
            Account account = iAccountService.deposit(userId, accountId, payload.get("amount"));
            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping("{id}/withdraw")
    public ResponseEntity<?> widthdrawAction(@RequestBody Map<String, Float> payload, @PathVariable("userId") Long userId, @PathVariable("id") Long accountId) throws Exception {
        try {
            Account account = iAccountService.withdraw(userId, accountId, payload.get("amount"));
            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (NotEnoughBalanceForWithdrawException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Account does not have enough balance", e);
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


