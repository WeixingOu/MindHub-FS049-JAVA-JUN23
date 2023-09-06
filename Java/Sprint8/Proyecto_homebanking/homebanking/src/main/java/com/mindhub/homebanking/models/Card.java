package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String cardHolder;
    private CardType cardType;
    private CardColor color;
    private String number;
    private String cvv;
    private LocalDate fromDate;
    private LocalDate truDate;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public Card() {}

    public Card(CardType cardType, CardColor color) {
        this.cardType = cardType;
        this.color = color;
        this.number = setCardNumber();
        this.cvv = setCvv();
        this.fromDate = LocalDate.now();
        this.truDate = LocalDate.now().plusYears(5);
    }

    public String setCardNumber() {
        Random random = new Random();
        return String.format("%04d-%04d-%04d-%04d",
            random.nextInt(9999),
            random.nextInt(9999),
            random.nextInt(9999),
            random.nextInt(9999));
    }

    public String setCvv() {
        Random random = new Random();
        return String.format("%03d",
            random.nextInt(999));
    }

    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CardType getType() {
        return cardType;
    }

    public void setType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getTruDate() {
        return truDate;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
