package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientLoanService clientLoanService;

    @RequestMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanService.getLoansDTO();
    }

    @PostMapping("/loans")
    @Transactional
    public ResponseEntity<Object> requestLoan(@RequestBody LoanApplicationDTO loan, Authentication authentication) {
        if (loan.getLoanId() == null || loan.getAmount() == null || loan.getAmount() <= 0 || loan.getPayments() == null || loan.getPayments() <= 0 || loan.getAccountNumberDest() == null || loan.getAccountNumberDest().isEmpty()) {
            return new ResponseEntity<>("Invalid input.", HttpStatus.FORBIDDEN);
        }

        if (loanService.getCurrentLoan(loan.getLoanId()).isEmpty()) {
            return new ResponseEntity<>("Loan does not exist.", HttpStatus.FORBIDDEN);
        }

        if (loan.getAmount() > loanService.getCurrentLoan(loan.getLoanId()).get().getMaxAmount()) {
            return new ResponseEntity<>("Requested amount exceeds loan max amount.", HttpStatus.FORBIDDEN);
        }

        if (!loanService.getCurrentLoan(loan.getLoanId()).get().getPayments().contains(loan.getPayments())) {
            return new ResponseEntity<>("Invalid quota selection.", HttpStatus.FORBIDDEN);
        }

        if (accountService.getCurrentAccount(loan.getAccountNumberDest()).isEmpty()) {
            return new ResponseEntity<>("Destination account does not exist.", HttpStatus.FORBIDDEN);
        }

        if (!accountService.getCurrentAccount(loan.getAccountNumberDest()).get().getClient().getEmail().equals(authentication.getName())) {
            return new ResponseEntity<>("Destination account does not belong to authenticated client.", HttpStatus.FORBIDDEN);
        }

        accountService.getCurrentAccount(loan.getAccountNumberDest()).get().setBalance(accountService.getCurrentAccount(loan.getAccountNumberDest()).get().getBalance() + (loan.getAmount() * 1.2));
        accountService.saveAccount(accountService.getCurrentAccount(loan.getAccountNumberDest()).get());

        Transaction transaction = new Transaction(loan.getAmount() * 1.2, TransactionType.CREDIT, loanService.getCurrentLoan(loan.getLoanId()).get().getName() + " loan approved");
        transaction.setAccount(accountService.getCurrentAccount(loan.getAccountNumberDest()).get());
        transactionService.saveTransaction(transaction);

        ClientLoan newClientLoan = new ClientLoan(loan.getAmount(), loan.getPayments(), clientService.getCurrentClient(authentication.getName()), loanService.getCurrentLoan(loan.getLoanId()).get());
        clientLoanService.saveClientLoan(newClientLoan);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
