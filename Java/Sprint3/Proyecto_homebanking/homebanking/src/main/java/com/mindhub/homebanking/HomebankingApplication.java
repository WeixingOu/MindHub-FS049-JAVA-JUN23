package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Loan;

import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.LoanRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository) {
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

			// Transacciones para la cuenta 1 del cliente 1
			Transaction transaction1 = new Transaction();
			transaction1.setType(TransactionType.CREDIT);
			transaction1.setAmount(25000.0);
			transaction1.setDate(new Date());
			transaction1.setAccount(account1);
			transaction1.setDescription("Depósito inicial");
			transactionRepository.save(transaction1);

			Transaction transaction2 = new Transaction();
			transaction2.setType(TransactionType.DEBIT);
			transaction2.setAmount(-5000.0);
			transaction2.setDate(new Date());
			transaction2.setAccount(account1);
			transaction2.setDescription("Retiro en ATM");
			transactionRepository.save(transaction2);

			// Transacciones para la cuenta 2 del cliente 1
			Transaction transaction4 = new Transaction();
			transaction4.setType(TransactionType.DEBIT);
			transaction4.setAmount(-6500.0);
			transaction4.setDate(new Date());
			transaction4.setAccount(account3);
			transaction4.setDescription("Retiro en ATM");
			transactionRepository.save(transaction4);

			// Transacciones para la cuenta 1 del cliente 2
			Transaction transaction3 = new Transaction();
			transaction3.setType(TransactionType.DEBIT);
			transaction3.setAmount(-6500.0);
			transaction3.setDate(new Date());
			transaction3.setAccount(account2);
			transaction3.setDescription("Retiro en ATM");
			transactionRepository.save(transaction3);
		};
	}
}
