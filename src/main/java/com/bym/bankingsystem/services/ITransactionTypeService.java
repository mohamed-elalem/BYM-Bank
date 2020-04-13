package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.transaction.TransactionType;

import java.util.List;
import java.util.Optional;

public interface ITransactionTypeService {
    public List<TransactionType> getAllTransactionTypes();
    public Optional<TransactionType> getSingleTransactionType(Long id);
    public TransactionType save(TransactionType transaction);
    public TransactionType update(TransactionType transaction);
    public void delete(Long id);

}
