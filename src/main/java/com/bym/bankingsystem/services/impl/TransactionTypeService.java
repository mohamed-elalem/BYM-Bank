package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import com.bym.bankingsystem.services.ITransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionTypeService implements ITransactionTypeService {

    @Autowired
    TransactionTypeRepository transactionTypeRepository;

    @Override
    public List<TransactionType> getAllTransactionTypes() {
        return this.transactionTypeRepository.findAll();
    }

    @Override
    public Optional<TransactionType> getSingleTransactionType(Long id) {
        return this.transactionTypeRepository.findById(id);
    }

    @Override
    public TransactionType save(TransactionType transaction) {
        return this.transactionTypeRepository.save(transaction);
    }

    @Override
    public TransactionType update(TransactionType transaction) {
        return this.transactionTypeRepository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        //this.transactionTypeRepository.delete(id);
    }
}
