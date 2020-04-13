package com.bym.bankingsystem.controllers;

import com.bym.bankingsystem.models.status.Response;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.services.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableCellRenderer;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/transaction-type")
public class TransactionTypeController {

    @Autowired
    ITransactionTypeService transactionTypeService;

    @GetMapping
    public Response findAll(){
        List<TransactionType> transactionTypes = this.transactionTypeService.getAllTransactionTypes();
        return new Response(200,"succeed",transactionTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionType> findTransactionTypeById(@PathVariable("id") Long id){
        Optional<TransactionType> transactionType =  transactionTypeService.getSingleTransactionType(id);
        if(transactionType.isPresent()){
            return ResponseEntity.ok(transactionType.get());
        } else {
            return  ResponseEntity.notFound().build();
        }
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
