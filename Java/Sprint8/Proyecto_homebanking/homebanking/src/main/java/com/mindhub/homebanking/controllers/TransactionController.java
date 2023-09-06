package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
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
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/transactions")
    @Transactional
    public ResponseEntity<Object> transfer(
        @RequestParam Double amount,
        @RequestParam String accountNumberFrom,
        @RequestParam String accountNumberTo,
        @RequestParam String description,
        Authentication authentication
    ) {
        if (amount == null || amount.isNaN() || accountNumberFrom.isEmpty() || accountNumberTo.isEmpty() || description.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (accountNumberFrom.equals(accountNumberTo)) {
            return new ResponseEntity<>("Source and destination accounts cannot be the same", HttpStatus.FORBIDDEN);
        }

        if (!accountRepository.findByNumber(accountNumberFrom).isPresent()) {
            return new ResponseEntity<>("Source account does not exist", HttpStatus.FORBIDDEN);
        }

        if (!accountRepository.findByNumber(accountNumberFrom).get().getClient().getEmail().equals(authentication.getName())){
            return new ResponseEntity<>("Source account does not belong to authenticated user", HttpStatus.FORBIDDEN);
        }

        if (!accountRepository.findByNumber(accountNumberTo).isPresent()) {
            return new ResponseEntity<>("Destination account does not exist", HttpStatus.FORBIDDEN);
        }

        if (accountRepository.findByNumber(accountNumberFrom).get().getBalance() < amount) {
            return new ResponseEntity<>("Insufficient balance in source account", HttpStatus.FORBIDDEN);
        }

        Transaction debitTransaction = new Transaction(-amount, TransactionType.DEBIT, description);
        debitTransaction.setAccount(accountRepository.findByNumber(accountNumberFrom).get());

        Transaction creditTransaction = new Transaction(amount, TransactionType.CREDIT, description);
        creditTransaction.setAccount(accountRepository.findByNumber(accountNumberTo).get());

        transactionRepository.save(debitTransaction);
        transactionRepository.save(creditTransaction);

        accountRepository.findByNumber(accountNumberFrom).get().setBalance(accountRepository.findByNumber(accountNumberFrom).get().getBalance() - amount);
        accountRepository.save(accountRepository.findByNumber(accountNumberFrom).get());

        accountRepository.findByNumber(accountNumberTo).get().setBalance(accountRepository.findByNumber(accountNumberTo).get().getBalance() + amount);
        accountRepository.save(accountRepository.findByNumber(accountNumberTo).get());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
