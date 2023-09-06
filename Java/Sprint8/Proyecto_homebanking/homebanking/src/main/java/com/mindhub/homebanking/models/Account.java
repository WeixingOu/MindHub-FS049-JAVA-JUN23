package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.Random;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String number;
    private Date creationDate;
    private Double balance;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions;

    public Account() { }

    public Account(Double balance) {
        this.number = generateNumber();
        this.creationDate = new Date();
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumber() {
        return number;
    }
    public String generateNumber() {
        Random random = new Random();
        int number = random.ints(10000000, 100000000).findFirst().getAsInt();
        return "VIN-" + number;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    @JsonIgnore
    public Set<Transaction> getTransactions() {
        return transactions;
    }
}

