package com.mindhub.homebanking.models;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private TransactionType type;

    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Transaction() {

    }

    public Transaction(Double amount, TransactionType type, Date date, String description) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public Double getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
    }

    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    public Date getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
