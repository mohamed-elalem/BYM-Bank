package com.bym.bankingsystem.bootstrap;

import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionTypesBootstrap implements ApplicationRunner {

    private TransactionTypeRepository transactionTypeRepository;

    public TransactionTypesBootstrap(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List.of("DEPOSIT", "WITHDRAW").stream()
                .forEach((name) -> {
                    Optional<TransactionType> transactionType = transactionTypeRepository.findByName(name);

                    if (transactionType.isEmpty()) {

                        transactionTypeRepository.save(TransactionType.create()
                                .withName(name)
                                .build());
                    }
                });
    }
}
