package com.mindhub.homebanking.services.impl;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
   @Autowired
   private CardRepository cardRepository;

    @Override
    public long countCardsByTypeAndClient(CardType cardType, Client client) {
        return cardRepository.countByCardTypeAndClient(cardType, client);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }
}
