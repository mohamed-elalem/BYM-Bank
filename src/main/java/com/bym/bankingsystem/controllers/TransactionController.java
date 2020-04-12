package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    ITransactionService transactionService;

    @GetMapping
    public Response findAll(){
        List<Transaction> transactions = this.transactionService.getAllTransactions();
        return new Response(200,"succeed",transactions);
    }

    @GetMapping("/{id}")
    public Transaction findTransactionById(@PathVariable("id") Long id){
        return transactionService.getSingleTransaction(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addNewTransaction(@RequestBody Transaction transactionToBeAdded) {
        transactionService.save(transactionToBeAdded);

    }
}
