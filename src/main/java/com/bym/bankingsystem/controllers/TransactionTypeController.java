package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.services.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-type")
public class TransactionTypeController {

    @Autowired
    ITransactionTypeService transactionTypeService;

    @GetMapping
    public Response findAll(){
        List<TransactionType> transactionTypes = this.transactionTypeService.getAllTransactionTypes();
        return new Response(200,"succeed",transactionTypes);
    }

    @GetMapping("/{id}")
    public TransactionType findTransactionTypeById(@PathVariable("id") Long id){
        return transactionTypeService.getSingleTransactionType(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addNewTransactionType(@RequestBody TransactionType transactionTypeToBeAdded) {
        transactionTypeService.save(transactionTypeToBeAdded);

    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateTransactionType(@RequestBody TransactionType transactionTypeToBeAdded) {
        transactionTypeService.update(transactionTypeToBeAdded);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTransactionType(@PathVariable("id") Long id) {
        transactionTypeService.delete(id);

    }
}
