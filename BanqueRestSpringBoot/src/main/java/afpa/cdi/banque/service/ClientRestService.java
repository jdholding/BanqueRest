/**
 * 
 */
package afpa.cdi.banque.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.metier.IClientMetier;
import afpa.cdi.banque.model.Client;

/**
 * @author 1603599
 * 
 * <br/><b>SERVICE REST QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les traitements métier relatifs à l'entité 'Client'.
 */
@RestController
public class ClientRestService {
	
	

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b>
	 * <br/>Les fonctionnalités d'écriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientRestService.class);
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES :</b>
	 * <br/>Les traitements métier relatifs à l'entité 'Client'.
	 */
	@Autowired
	private IClientMetier clientMetier;

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Ajouter ou modifier un client dans les données persistantes.
	 * 
	 * @param pClient Le client avec lequel l'opération doit être effectuée.
	 * @return Client Le client ajouté.
	 * @throws InvalidArgumentException Lancée dans le cas : Le client fourni est invalide.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération demandée est illégale.
	 * @see afpa.cdi.banque.metier.IClientMetier#addOrUpdate(afpa.cdi.banque.model.Client)
	 */
	@RequestMapping(value="/client", method=RequestMethod.POST)
	@ResponseBody
	public Client add(@RequestBody Client pClient) throws InvalidArgumentException, IllegalOperationException {
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : add -- BEGIN");

		Client clientCreated = clientMetier.addOrUpdate(pClient);
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : add -- END");
		return clientCreated;
	}

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher un client dans les données persistantes.
	 * <br/>Critère de recherche : Le code du client.
	 * 
	 * @param pCode Le code du client à rechercher.
	 * @return Client Le client trouvé.
	 * @see afpa.cdi.banque.metier.IClientMetier#find(java.lang.Long)
	 */
	@RequestMapping(value="/client/{pCode}", method=RequestMethod.GET)
	public Client find(@PathVariable Long pCode) {
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : find -- BEGIN");

		Client clientFound = clientMetier.find(pCode);
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : find -- END");
		return clientFound;
	}

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de clients dans les données persistantes.
	 * <br/>Critère de recherche : Aucun (-> rechercher tous les clients).
	 * 
	 * @return List<Client> La collection des clients trouvés.
	 * @see afpa.cdi.banque.metier.IClientMetier#findAll()
	 */
	@RequestMapping(value="/clients/all", method=RequestMethod.GET)
	public List<Client> findAll() {
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : findAll -- BEGIN");

		List<Client> clientsFound = clientMetier.findAll();
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : findAll -- END");
		return clientsFound;
	}

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de clients dans les données persistantes.
	 * <br/>Critère de recherche : Le nom du client.
	 * 
	 * @param pNom Le nom du client à rechercher.
	 * @return List<Client> La collection des clients trouvés.
	 * @see afpa.cdi.banque.metier.IClientMetier#findByNom(java.lang.String)
	 */
	@RequestMapping(value="/clients/{pNom}", method=RequestMethod.GET)
	public List<Client> findByNom(@RequestParam(value="nom", required=true) String pNom) {
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : findByNom -- BEGIN");

		List<Client> clientsFound = clientMetier.findByNom(pNom);
		
		LOGGER.info("CLASS : ClientRestService -- METHOD : findByNom -- END");
		return clientsFound;
	}
}
