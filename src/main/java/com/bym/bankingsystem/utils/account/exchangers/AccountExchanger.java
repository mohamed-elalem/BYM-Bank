package com.bym.bankingsystem.utils.account.exchangers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.models.transaction.Transaction;
import com.bym.bankingsystem.models.transaction.TransactionType;
import com.bym.bankingsystem.repositories.TransactionRepository;
import com.bym.bankingsystem.repositories.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountExchanger {
    private final UserRepository userRepository;
    private Exchanger exchanger;
    private TransactionRepository transactionRepository;
    private RabbitTemplate rabbitTemplate;

    public AccountExchanger(
            TransactionRepository transactionRepository,
            RabbitTemplate rabbitTemplate,
            UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
    }

    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    public Account exchange(Account account, TransactionType transactionType, Float amount) throws Exception {
        account = this.exchanger.exchange(account, transactionType, amount);
        List<Transaction> transactions = account.getTransactionsFrom();
        Transaction transaction = transactions.get(transactions.size() - 1);

        rabbitTemplate.convertAndSend("accountActions", "", actionPayload(account, transaction));

        return account;
    }

    private Map<String, String> actionPayload(Account account, Transaction transaction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        User loggedInUser = userRepository.findByUsername(loggedInUsername);

        Map<String, String> payload = new HashMap<>() {{
            put("accountId", account.getId().toString());
            put("transactionId", transaction.getId().toString());
            put("tellerId", loggedInUser.getId().toString());
        }};

        return payload;
    }
}
