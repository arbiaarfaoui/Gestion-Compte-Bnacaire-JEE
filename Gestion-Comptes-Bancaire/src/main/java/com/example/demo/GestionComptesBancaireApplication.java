package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;
import com.example.demo.metier.IBanqueMetier;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.OperationRepository;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GestionComptesBancaireApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;

	public static void main(String[] args) {
		SpringApplication.run(GestionComptesBancaireApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("Arbia", "arbia@gmail.com"))	;	
		Client c2 = clientRepository.save(new Client("Kevin", "kevin@gmail.com"))	;
		Client client = clientRepository.save(new Client("Arfaoui Arbia", "arfaoui.arbia@yahoo.com"));
	
		Compte cp1 = compteRepository.save(new CompteCourant("cp1", new Date(), 90000, c1, 6000));
		Compte cp2 = compteRepository.save(new CompteEpargne("cp2", new Date(), 6000, c2, 5.5));
		Compte D0192C11199C2005=compteRepository.save(new CompteCourant("D0192C11199C2005",new Date(),10000,client,7800));
		
		operationRepository.save(new Versement(new Date(),9000 , cp1) );
		operationRepository.save(new Versement(new Date(),6000 , cp1) );
		operationRepository.save(new Versement(new Date(),2300 , cp1) );
		operationRepository.save(new Retrait(new Date(),9000 , cp1) );
		
		operationRepository.save(new Versement(new Date(),2300 , cp2) );
		operationRepository.save(new Versement(new Date(),400 , cp2) );
		operationRepository.save(new Versement(new Date(),2300 , cp2) );
		operationRepository.save(new Retrait(new Date(),3000 , cp2) );

		operationRepository.save(new Versement(new Date(),8500 , D0192C11199C2005) );
		operationRepository.save(new Versement(new Date(),6070 , D0192C11199C2005) );
		operationRepository.save(new Versement(new Date(),2400 , D0192C11199C2005) );
		operationRepository.save(new Retrait(new Date(),1000 , D0192C11199C2005) );
		
		banqueMetier.verser("cp1", 111111);
		banqueMetier.verser("D0192C11199C2005",2050);
	}
}
