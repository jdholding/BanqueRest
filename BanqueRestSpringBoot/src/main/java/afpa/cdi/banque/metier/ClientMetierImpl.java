/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.cdi.banque.dao.IClientRepository;
import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.model.Client;

/**
 * @author 1603599
 *
 * <br/><b>CLASSE QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les traitements métier relatifs à l'entité 'Client'.
 */
@Service
public class ClientMetierImpl implements IClientMetier {

	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b>
	 * <br/>Les fonctionnalités d'écriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientMetierImpl.class);
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES :</b>
	 * <br/>Les fonctionnalités de persistance relatives à l'entité 'Client'.
	 */
	@Autowired
	private IClientRepository clientRepository;
	

	
	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.IClientMetier#addOrUpdate(afpa.cdi.banque.model.Client)
	 */
	@Override
	public Client addOrUpdate(Client pClient) throws InvalidArgumentException, IllegalOperationException {
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : addOrUpdate -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		// (01.)TRAITER LE CAS D'ERREUR SUIVANT :
		//      ->LE CLIENT FOURNI (EN PARAMETRE) EST INVALIDE.
		///////////////////////////////////////////////////////////////////////
		checkValidity(pClient);

		///////////////////////////////////////////////////////////////////////
		// (02.)TRAITER LES CAS SUIVANTS :
		//      ->(02.01.)LE CLIENT FOURNI POSSEDE UN CODE NULL.
		//      ->(02.02.)LE CLIENT FOURNI POSSEDE UN CODE NON NULL.
		///////////////////////////////////////////////////////////////////////
		if (pClient.getCode() == null) {
			///////////////////////////////////////////////////////////////////////
			// (02.01.)LE CLIENT FOURNI N'EXISTE PAS (DANS LES DONNEES PERSISTANTES).
			//         ->EFFECTUER L'OPERATION SUIVANTE : 
			//             AJOUTER LE CLIENT DANS LES DONNEES PERSISTANTES.
			///////////////////////////////////////////////////////////////////////
			Client clientCreated = add(pClient);
			
			LOGGER.info("CLASS : ClientMetierImpl -- METHOD : addOrUpdate -- END");
			return clientCreated;
			
		} else {
			///////////////////////////////////////////////////////////////////////
			// (02.02.)LE CLIENT FOURNI EXISTE DEJA (DANS LES DONNEES PERSISTANTES).
			//         ->EFFECTUER L'OPERATION SUIVANTE : 
			//             MODIFIER LE CLIENT DANS LES DONNEES PERSISTANTES.
			///////////////////////////////////////////////////////////////////////
			Client clientUpdated = update(pClient); 
			
			LOGGER.info("CLASS : ClientMetierImpl -- METHOD : addOrUpdate -- END");
			return clientUpdated;
		}
	}

	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.IClientMetier#find(java.lang.Long)
	 */
	@Override
	public Client find(Long pCode) {
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : find -- BEGIN");
		
		Client clientFound = clientRepository.findOne(pCode);
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : find -- END");
		return clientFound;
	}
	
	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.IClientMetier#findAll()
	 */
	@Override
	public List<Client> findAll() {
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : findAll -- BEGIN");
		
		List<Client> clientsFound = this.clientRepository.findAll();
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : findAll -- END");
		return clientsFound;
	}

	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.IClientMetier#findByNom(java.lang.String)
	 */
	@Override
	public List<Client> findByNom(String pNom) {
		List<Client> clientsFound = this.clientRepository.findByNom(pNom);
		return clientsFound;
	}
	
	
	
	/**
	 * <b>METHODE QUI EFFECTUE L'OPERATION SUIVANTE :</b>
	 * <br/>Ajouter un client dans les données persistantes :
	 * 
	 * @param pClient Le client fourni (avec lequel l'ajout doit être effectué).
	 * @return Client Le client ajouté.
	 */
	private Client add(Client pClient) {
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : add -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		// (01.)EFFECTUER L'OPERATION SUIVANTE :
		//      ->SAUVEGARDER LE CLIENT DANS LES DONNEES PERSISTANTES.
		///////////////////////////////////////////////////////////////////////
		Client clientCreated = this.clientRepository.save(pClient);
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : add -- END");
		return clientCreated;
	}
	
	/**
	 * <b>METHODE QUI EFFECTUE L'OPERATION SUIVANTE :</b>
	 * <br/>Modifier un client dans les données persistantes :
	 * 
	 * @param pClient Le client fourni (avec lequel la modification doit être effectuée).
	 * @return Client Le client modifié.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération est illégale.
	 */
	private Client update(Client pClient) throws IllegalOperationException {
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : update -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		// (01.)VERIFIER LA LEGALITE DE L'OPERATION SUIVANTE :
		//      ->LA MODIFICATION DU CLIENT FOURNI.
		///////////////////////////////////////////////////////////////////////
		checkLegalityOfUpdate(pClient);
		
		///////////////////////////////////////////////////////////////////////
		// (02.)EFFECTUER L'OPERATION SUIVANTE :
		//      ->SAUVEGARDER LE CLIENT MODIFIE DANS LES DONNEES PERSISTANTES.
		///////////////////////////////////////////////////////////////////////
		Client clientUpdated = this.clientRepository.save(pClient);
		
		LOGGER.info("CLASS : ClientMetierImpl -- METHOD : update -- END");
		return clientUpdated;
	}
	
	
	
	/**
	 * <b>METHODE QUI EFFECTUE LA VERIFICATION SUIVANTE :</b>
	 * <br/>Vérifier la légalité de l'opération suivante :
	 * <br/>La modification d'un client dans les données persistances.
	 * 
	 * @param pClient Le client fourni (avec lequel la modification doit être effectuée).
	 * @return boolean 
	 *         <br/>VRAI : Cas où l'opération est légale. 
	 *         <br/>FAUX : Cas où l'opération est illégale.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération est illégale.
	 */
	private boolean checkLegalityOfUpdate(Client pClient) throws IllegalOperationException {
		
		///////////////////////////////////////////////////////////////////////
		// (01.)RECHERCHER L'OBJET 'CLIENT' (DANS LES DONNEES PERSISTANTES) :
		//      ->CRITERE DE RECHERCHE : LE CODE DU CLIENT.
		//      ->VALEUR UTILISEE : LE CODE DU CLIENT FOURNI.
		///////////////////////////////////////////////////////////////////////
		Client clientFound = this.clientRepository.findOne(pClient.getCode());
		
		///////////////////////////////////////////////////////////////////////
		// (02.)TRAITER LES CAS SUIVANTS :
		//      ->(02.01.)LE CLIENT FOURNI N'EXISTE PAS (DANS LES DONNEES PERSISTANTES).
		//      ->(02.02.)LE CLIENT FOURNI EXISTE DEJA (DANS LES DONNEES PERSISTANTES).
		///////////////////////////////////////////////////////////////////////
		if (clientFound == null) {
			LOGGER.error("Le client -- Valeur : null");
			throw new IllegalOperationException("->Le client -- Valeur : null");
		}
		return true;
	}
	
	/**
	 * <b>METHODE QUI EFFECTUE LA VERIFICATION SUIVANTE :</b>
	 * <br/>Vérifier la validité d'un client.
	 * 
	 * @param pClient Le client à valider.
	 * @return boolean 
	 *         <br/>VRAI : Cas où le client est valide. 
	 *         <br/>FAUX : Cas où le client est invalide.
	 * @throws InvalidArgumentException Lancée dans le cas : Le client est invalide.
	 */
	private boolean checkValidity(Client pClient) throws InvalidArgumentException {
		
		///////////////////////////////////////////////////////////////////////
		// (01.)TRAITER LE CAS SUIVANT :
		//      ->LE CLIENT FOURNI EST NULL.
		///////////////////////////////////////////////////////////////////////
		if (pClient == null) {
			LOGGER.error("Le client -- Valeur : null");
			throw new InvalidArgumentException("->Le client -- Valeur : null");
		}
		///////////////////////////////////////////////////////////////////////
		// (02.)TRAITER LE CAS SUIVANT :
		//      ->DANS LE CLIENT FOURNI, L'ATTRIBUT 'NOM' EST NULL.
		///////////////////////////////////////////////////////////////////////
		if (pClient.getNom() == null) {
			LOGGER.error("Le nom du client -- Valeur : null");
			throw new InvalidArgumentException("->Le nom du client -- Valeur : null");
		}
		///////////////////////////////////////////////////////////////////////
		// (03.)TRAITER LE CAS SUIVANT :
		//      ->DANS LE CLIENT FOURNI, LES ATTRIBUTS SUIVANTS SONT NON NULL :
		//          ->L'ATTRIBUT 'NOM'
		///////////////////////////////////////////////////////////////////////
		return true;
	}
}
