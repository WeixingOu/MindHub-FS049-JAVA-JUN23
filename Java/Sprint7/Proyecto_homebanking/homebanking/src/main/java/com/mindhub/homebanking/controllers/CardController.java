package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(
        @RequestParam CardColor cardColor,
        @RequestParam CardType cardType,
        Authentication authentication
        ) {
            Client currentClient = clientRepository.findByEmail(authentication.getName()).orElse(null);

            if (cardRepository.countByCardTypeAndClient(cardType, currentClient) >= 3) {
                return new ResponseEntity<>("Limit reached for this card type", HttpStatus.FORBIDDEN);
            }

            Card newCard = new Card(cardType, cardColor);
            newCard.setClient(currentClient);
            newCard.setCardHolder(currentClient.getFirstName() + " " + currentClient.getLastName());
            cardRepository.save(newCard);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
}
