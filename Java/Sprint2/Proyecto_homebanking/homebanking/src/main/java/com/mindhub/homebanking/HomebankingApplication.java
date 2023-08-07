package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
		return (args) -> {
			Client client1 = new Client("melba@mindhub.com", "Melba", "Morel");
			Client client2 = new Client("juan@mindhub.com", "Juan", "Perez");

			clientRepository.save(client1);
			clientRepository.save(client2);

			// Creando la cuenta para el cliente 1
			Account account1 = new Account(new Date(), 5000.0);
			account1.setClient(client1);
			accountRepository.save(account1);
			account1.generateNumber();
			accountRepository.save(account1);

			Account account3 = new Account(new Date(), 7500.0);
			account3.setClient(client1);
			accountRepository.save(account3);
			account3.generateNumber();
			accountRepository.save(account3);

			// Creando la cuenta para el cliente 2
			Account account2 = new Account(new Date(), 1111.0);
			account2.setClient(client2);
			accountRepository.save(account2);
			account2.generateNumber();
			accountRepository.save(account2);

			client1.addAccount(account1);
			client2.addAccount(account2);
			clientRepository.save(client1);
			clientRepository.save(client2);
		};
	}
}
