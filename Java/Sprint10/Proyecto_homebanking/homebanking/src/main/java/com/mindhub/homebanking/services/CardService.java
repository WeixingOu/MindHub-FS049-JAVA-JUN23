package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;

public interface CardService {
    long countCardsByTypeAndClient(CardType cardType, Client client);

    void saveCard(Card card);
}
