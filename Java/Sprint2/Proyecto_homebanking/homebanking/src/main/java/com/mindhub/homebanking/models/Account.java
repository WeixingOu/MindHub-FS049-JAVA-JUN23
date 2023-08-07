package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Date;
import java.util.Locale;

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


    public Account() { }

    public Account(Date creationDate, Double balance) {
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }
    public void generateNumber() {
        String initials = client.getFirstName().substring(0, 2) + client.getLastName().substring(0, 1);
        this.number = initials.toUpperCase(Locale.ROOT) + "-" +  String.format("%05d", client.getId()) + this.id;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
}

