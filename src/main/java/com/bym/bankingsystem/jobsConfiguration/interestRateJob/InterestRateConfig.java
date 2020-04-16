package com.bym.bankingsystem.jobsConfiguration.interestRateJob;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.repositories.TransactionRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class InterestRateConfig {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Bean
    public RepositoryItemReader<Account> accountInterestRateReader() {
        RepositoryItemReader<Account> reader = new RepositoryItemReader<Account>();
        reader.setRepository(accountRepository);
        reader.setMethodName("getAccountWithInterestRate");
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        reader.setSort(sort);
        return reader;
    }

    @Bean
    public InterestRateTransactionProcessor accountInterestRateProcessor() {
        return (new InterestRateTransactionProcessor());
    }

    @Bean
    public RepositoryItemWriter<Transaction> InterestRateTransactionWriter(TransactionRepository transactionRepository) {
        RepositoryItemWriter<Transaction> writer = new RepositoryItemWriter<>();
        writer.setRepository(transactionRepository);
        writer.setMethodName("save");

        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer;
    }

    @Bean
    public Step interestRateTransaction() {
        return stepBuilders.get("interestRateTransaction")
                .<Account, Transaction>chunk(5)
                .reader(accountInterestRateReader())
                .processor(accountInterestRateProcessor())
                .writer(InterestRateTransactionWriter(transactionRepository))
                .build();
    }

    @Bean
    public Job accountsInterestRate() {
        return jobBuilders.get("accountsInterestRate")
                .start(interestRateTransaction())
                .build();
    }
}
