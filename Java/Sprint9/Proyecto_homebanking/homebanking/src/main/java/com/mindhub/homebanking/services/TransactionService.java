package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;

public interface TransactionService {
    Boolean isTransactionDataValid(Double amount, String accountNumberFrom, String accountNumberTo, String description);

    void saveTransaction(Transaction transaction);
}
