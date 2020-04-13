package com.bym.bankingsystem.utils.account.exchangers;

import com.bym.bankingsystem.models.account.Account;
import com.bym.bankingsystem.models.transaction.TransactionType;

public interface Exchanger {
    Account exchange(Account account, TransactionType transactionType, Float amount) throws Exception;
}
