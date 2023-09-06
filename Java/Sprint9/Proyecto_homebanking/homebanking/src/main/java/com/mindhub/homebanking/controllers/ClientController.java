package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClientsDTO();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientDTO(id);
    }

    @RequestMapping("clients/current")
    public ClientDTO getCurrentClient(Authentication authentication) {
        return clientService.getCurrentClientDTO(authentication);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> registerClient(
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam String email,
        @RequestParam String password
    ) {
        if (clientService.isClientDataValid(firstName, lastName, email, password)) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientService.isClientAlreadyUse(email)) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Client newClient = new Client(email, firstName, lastName, passwordEncoder.encode(password));
        clientService.saveClient(newClient);

        Account newAccount = new Account(0.0);
        newAccount.setClient(newClient);
        accountService.saveAccount(newAccount);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}