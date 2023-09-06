package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClientsDTO();

    ClientDTO getClientDTO(long id);

    ClientDTO getCurrentClientDTO(Authentication authentication);

    Boolean isClientDataValid(String firstName, String lastName, String email, String password);

    Boolean isClientAlreadyUse(String email);

    void saveClient(Client client);

    Client getCurrentClient(String email);
}
