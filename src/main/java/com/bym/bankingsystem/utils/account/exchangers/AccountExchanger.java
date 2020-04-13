package com.bym.bankingsystem.utils.account.exchangers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class AccountExchanger {
    private Exchanger exchanger;

    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    public Account exchange(Account account, TransactionType transactionType, Float amount) throws Exception {
        return this.exchanger.exchange(account, transactionType, amount);
    }
}
