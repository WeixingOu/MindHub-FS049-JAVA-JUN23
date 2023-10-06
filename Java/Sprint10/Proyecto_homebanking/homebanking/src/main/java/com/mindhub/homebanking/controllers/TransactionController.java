package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/transactions")
    @Transactional
    public ResponseEntity<Object> transfer(
        @RequestParam Double amount,
        @RequestParam String accountNumberFrom,
        @RequestParam String accountNumberTo,
        @RequestParam String description,
        Authentication authentication
    ) {
        if (transactionService.isTransactionDataValid(amount, accountNumberFrom, accountNumberTo, description)) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (accountNumberFrom.equals(accountNumberTo)) {
            return new ResponseEntity<>("Source and destination accounts cannot be the same", HttpStatus.FORBIDDEN);
        }

        if (accountService.getCurrentAccount(accountNumberFrom).isEmpty()) {
            return new ResponseEntity<>("Source account does not exist", HttpStatus.FORBIDDEN);
        }

        if (accountService.getCurrentAccount(accountNumberFrom).get().getClient().getEmail().equals(authentication.getName())){
            return new ResponseEntity<>("Source account does not belong to authenticated user", HttpStatus.FORBIDDEN);
        }

        if (accountService.getCurrentAccount(accountNumberTo).isEmpty()) {
            return new ResponseEntity<>("Destination account does not exist", HttpStatus.FORBIDDEN);
        }

        if (accountService.getCurrentAccount(accountNumberFrom).get().getBalance() < amount) {
            return new ResponseEntity<>("Insufficient balance in source account", HttpStatus.FORBIDDEN);
        }

        Transaction debitTransaction = new Transaction(-amount, TransactionType.DEBIT, description);
        debitTransaction.setAccount(accountService.getCurrentAccount(accountNumberFrom).get());

        Transaction creditTransaction = new Transaction(amount, TransactionType.CREDIT, description);
        creditTransaction.setAccount(accountService.getCurrentAccount(accountNumberTo).get());

        transactionService.saveTransaction(debitTransaction);
        transactionService.saveTransaction(creditTransaction);

        accountService.getCurrentAccount(accountNumberFrom).get().setBalance(accountService.getCurrentAccount(accountNumberFrom).get().getBalance() - amount);
        accountService.saveAccount(accountService.getCurrentAccount(accountNumberFrom).get());

        accountService.getCurrentAccount(accountNumberTo).get().setBalance(accountService.getCurrentAccount(accountNumberTo).get().getBalance() + amount);
        accountService.saveAccount(accountService.getCurrentAccount(accountNumberTo).get());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
