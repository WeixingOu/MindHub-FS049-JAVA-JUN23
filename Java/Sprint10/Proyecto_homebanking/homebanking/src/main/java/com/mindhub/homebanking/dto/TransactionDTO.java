package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.util.Date;

public class TransactionDTO {
    private Double amount;
    private TransactionType type;
    private Date date;
    private String description;

    public TransactionDTO(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
    }

    public Double getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
    }
    public Date getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
}
