package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.models.transaction.TransactionType;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    public List<Transaction> getAllTransactions();
    public Optional<Transaction>  getSingleTransaction(Long id);
    public Transaction save(Transaction transaction);
    // transaction should be deleted by reverting the transaction not by deleting any records
    // public void delete(Long id);
}
