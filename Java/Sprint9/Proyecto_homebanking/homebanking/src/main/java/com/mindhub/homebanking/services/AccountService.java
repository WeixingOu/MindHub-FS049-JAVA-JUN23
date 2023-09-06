package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void saveAccount(Account account);

    List<AccountDTO> getAccountsDTO();

    AccountDTO getAccountDTO(long id);

    long countAccountsByClient(Client client);

    Optional<Account> getCurrentAccount(String accountNumber);
}
