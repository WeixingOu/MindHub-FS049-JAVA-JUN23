package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.util.Date;

public class AccountDTO {
    private String number;
    private Date creationDate;
    private Double balance;

    public AccountDTO(Account account) {
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
    }

    public String getNumber() {
        return number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }
}
