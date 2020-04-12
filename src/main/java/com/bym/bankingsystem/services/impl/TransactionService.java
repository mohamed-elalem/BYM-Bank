package com.bym.bankingsystem.services.impl;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.repositories.TransactionRepository;
import com.bym.bankingsystem.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public Transaction getSingleTransaction(Long id) {
        return this.transactionRepository.getOne(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
}
