package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.transaction.Transaction;

import java.util.List;

public interface ITransactionService {
    public List<Transaction> getAllTransactions();
    public Transaction getSingleTransaction(Long id);
    public Transaction save(Transaction transaction);
    // transaction should be deleted by reverting the transaction not by deleting any records
    // public void delete(Long id);
}
