package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.transaction.TransactionType;

import java.util.List;

public interface ITransactionTypeService {
    public List<TransactionType> getAllTransactionTypes();
    public TransactionType getSingleTransactionType(Long id);
    public TransactionType save(TransactionType transaction);
    public TransactionType update(TransactionType transaction);
    public void delete(Long id);

}
