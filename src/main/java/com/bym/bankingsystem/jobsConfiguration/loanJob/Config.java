package com.bym.bankingsystem.jobsConfiguration.loanJob;

import com.bym.bankingsystem.models.loan.Loan;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.repositories.AccountRepository;
import com.bym.bankingsystem.repositories.ILoanRepository;
import com.bym.bankingsystem.repositories.TransactionRepository;
import com.bym.bankingsystem.repositories.TransactionTypeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.*;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class Config {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    ILoanRepository iLoanRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionTypeRepository transactionTypeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Bean
    public RepositoryItemReader<Loan> reader(){
        RepositoryItemReader<Loan> reader = new RepositoryItemReader<Loan>();
        reader.setRepository(iLoanRepository);
        reader.setMethodName("getActiveLoans");
        List parameters = new ArrayList();
        reader.setArguments(parameters);
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        reader.setSort(sort);
        return reader;
    }

    @Bean
    public CompositeItemProcessor processor() throws Exception{
        List<ItemProcessor> delegates = new ArrayList<>();
        delegates.add(new LoanProcessor());
        delegates.add(new LoanCustomerAccountProcessor());
        delegates.add(new TransactionProcessor(accountRepository, transactionTypeRepository));
        delegates.add(new LoanBankAccountProcessor(accountRepository));
        CompositeItemProcessor processor = new CompositeItemProcessor<>();
        processor.setDelegates(delegates);
        processor.afterPropertiesSet();
        return processor;
    }

    @Bean
    public RepositoryItemWriter<Transaction> transactionWriter(TransactionRepository transactionRepository) {
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
    public Step transaction() throws Exception {
        return stepBuilders.get("transaction")
                .<Loan,Transaction>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(transactionWriter(transactionRepository))
                .build();
    }

    @Bean
    public Job loanTransaction() throws Exception {
        return jobBuilders.get("loanTransaction")
                .start(transaction())
                .build();
    }


}
