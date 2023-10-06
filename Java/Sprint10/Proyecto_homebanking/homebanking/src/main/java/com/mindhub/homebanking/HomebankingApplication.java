package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;

import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class);
	}

	/*@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {
			Client adminClient = new Client("admin@mindhub.com", "admin", "admin", passwordEncoder.encode("admin"));
			clientRepository.save(adminClient);

			Client client1 = new Client("melba@mindhub.com", "Melba", "Morel", passwordEncoder.encode("melba"));
			Client client2 = new Client("juan@mindhub.com", "Juan", "Perez", passwordEncoder.encode("perezpass"));

			clientRepository.save(client1);
			clientRepository.save(client2);

			// Creando la cuenta para el cliente 1
			Account account1 = new Account(5000.0);
			account1.setClient(client1);
			accountRepository.save(account1);

			Account account3 = new Account(7500.0);
			account3.setClient(client1);
			accountRepository.save(account3);

			// Creando la cuenta para el cliente 2
			Account account2 = new Account(1111.0);
			account2.setClient(client2);
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
			transaction1.setDescription("Initial deposit");
			transactionRepository.save(transaction1);

			Transaction transaction2 = new Transaction();
			transaction2.setType(TransactionType.DEBIT);
			transaction2.setAmount(-5000.0);
			transaction2.setDate(new Date());
			transaction2.setAccount(account1);
			transaction2.setDescription("ATM withdrawal");
			transactionRepository.save(transaction2);

			// Transacciones para la cuenta 2 del cliente 1
			Transaction transaction4 = new Transaction();
			transaction4.setType(TransactionType.DEBIT);
			transaction4.setAmount(-6500.0);
			transaction4.setAccount(account3);
			transaction4.setDescription("ATM withdrawal");
			transactionRepository.save(transaction4);

			// Transacciones para la cuenta 1 del cliente 2
			Transaction transaction3 = new Transaction();
			transaction3.setType(TransactionType.DEBIT);
			transaction3.setAmount(-6500.0);
			transaction3.setAccount(account2);
			transaction3.setDescription("ATM withdrawal");
			transactionRepository.save(transaction3);

			// Creando los préstamos
			Loan loanMortgage = new Loan("Mortgage", 500000.0,  new HashSet<>(List.of(12, 24, 36, 48, 60)));
			Loan loanPersonal = new Loan("Personal", 100000.0,  new HashSet<>(List.of(6, 12, 24)));
			Loan loanCollateral = new Loan("Collateral", 300000.0,  new HashSet<>(List.of(6, 12, 24, 36)));

			loanRepository.saveAll(List.of(loanMortgage, loanPersonal, loanCollateral));

			// Creando los préstamos para cliente1
			ClientLoan client1Loan1 = new ClientLoan(400000.0, 60, client1, loanMortgage);
			ClientLoan client1Loan2 = new ClientLoan(50000.0, 12, client1, loanPersonal);
			clientLoanRepository.saveAll(List.of(client1Loan1, client1Loan2));

			// Creando los préstamos para cliente2
			ClientLoan client2Loan1 = new ClientLoan(100000.0, 24, client2, loanPersonal);
			ClientLoan client2Loan2 = new ClientLoan(200000.0, 36, client2, loanCollateral);
			clientLoanRepository.saveAll(List.of(client2Loan1, client2Loan2));

			// Creando las tarjetas para cliente1
			Card cardDebitMelba = new Card(CardType.DEBIT, CardColor.GOLD);
			cardDebitMelba.setCardHolder(client1.getFirstName() + " " + client1.getLastName());
			cardDebitMelba.setClient(client1);
			cardRepository.save(cardDebitMelba);

			Card cardCreditMelba = new Card(CardType.CREDIT, CardColor.TITANIUM);
			cardCreditMelba.setCardHolder(client1.getFirstName() + " " + client1.getLastName());
			cardCreditMelba.setClient(client1);
			cardRepository.save(cardCreditMelba);

			// Creando las tarjetas para cliente2
			Card cardCreditJuan = new Card(CardType.CREDIT, CardColor.TITANIUM);
			cardCreditJuan.setCardHolder(client2.getFirstName() + " " + client2.getLastName());
			cardCreditJuan.setClient(client2);
			cardRepository.save(cardCreditJuan);
		};
	}*/
}
