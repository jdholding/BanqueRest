package afpa.cdi.banque;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.metier.IClientMetier;
import afpa.cdi.banque.metier.ICompteMetier;
import afpa.cdi.banque.model.Client;
import afpa.cdi.banque.model.CompteCourant;
import afpa.cdi.banque.model.CompteEpargne;

@SpringBootApplication
public class BanqueApplication {

	public static void main(String[] args) {
		
		System.out.println("+---- ETAPE 00 : RECUPERER LE CONTEXTE DE L'APPLICATION -------------------------------+");
		ApplicationContext context = SpringApplication.run(BanqueApplication.class, args);

		System.out.println("+---- ETAPE 01 : RECUPERER LES SERVICES NECESSAIRES -----------------------------------+");
		IClientMetier clientMetier = context.getBean(IClientMetier.class); 
		ICompteMetier compteMetier = context.getBean(ICompteMetier.class); 
		
		System.out.println("+---- ETAPE 02 : CREER DES CLIENTS ----------------------------------------------------+");
		Client clientNonPersiste = new Client("ClientNonPersiste");
		Client clientTcharou = new Client("ClientTcharou");
		
		System.out.println("+---- ETAPE 03 : INSERER EN BDD CERTAINS CLIENTS --------------------------------------+");
		try {
			clientTcharou = clientMetier.addOrUpdate(clientTcharou);
		} catch (InvalidArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalOperationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		clientNonPersiste.setCode(Long.valueOf(1450));
		Long codeNonPersiste = clientNonPersiste.getCode();
		Long codeTcharou = clientTcharou.getCode();
		
		System.out.println("CLIENT CREE -- Nom : ClientNonPersiste -- Code : " + codeNonPersiste);
		System.out.println("CLIENT CREE -- Nom : ClientTcharou -- Code : " + codeTcharou);
		
		System.out.println("+---- ETAPE 04 : CREER DES COMPTES PUIS LES RATTACHER A UN CLIENT ---------------------+");
		CompteCourant compteCourantOrphelin = new CompteCourant("CC-00000-01", new Date(), 1000.00, 50.00);
		
		CompteCourant compteCourantTcharou = new CompteCourant("CC-00001-01", new Date(), 1000.00, 50.00);
		CompteEpargne compteEpargneTcharou = new CompteEpargne("CE-00001-01", new Date(), 1000.00, 0.045);
		
		compteCourantOrphelin.setClient(clientNonPersiste);
		compteCourantTcharou.setClient(clientTcharou);
		compteEpargneTcharou.setClient(clientTcharou);
		
		System.out.println("+---- ETAPE 05 : INSERER LES COMPTES EN BDD -------------------------------------------+");
		try {
			compteMetier.addOrUpdate(compteCourantOrphelin);
			compteMetier.addOrUpdate(compteCourantTcharou);
		} catch (Exception e) {
			System.out.println("CREATION DE COMPTES : ERREUR -- MESSAGE : " + e.getMessage());
		}
		
		System.out.println("+---- ETAPE 06 : RECHERCHE DE TOUS LES CLIENTS ----------------------------------------+");
		List<Client> clientsFoundAll = clientMetier.findAll();
		clientsFoundAll.forEach(c->System.out.println(c.getNom()));
		
		System.out.println("+---- ETAPE 07 : RECHERCHE DE CLIENTS AVEC CRITERES PARTICULIERS ----------------------+");
		List<Client> clientsFoundByCriteria = clientMetier.findByNom("%A%");
		clientsFoundByCriteria.forEach(c->System.out.println(c.getNom()));
		
		System.out.println("+---- ETAPE 08 : FIN DU PROGRAMME -----------------------------------------------------+");
		
	}
}
