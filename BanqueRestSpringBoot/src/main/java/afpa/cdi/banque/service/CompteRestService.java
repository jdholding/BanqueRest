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
import afpa.cdi.banque.metier.ICompteMetier;
import afpa.cdi.banque.model.Compte;

/**
 * @author 1603599
 * 
 * <br/><b>SERVICE REST QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les traitements métier relatifs à l'entité 'Compte'.
 */
@RestController
public class CompteRestService {

	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b>
	 * <br/>Les fonctionnalités d'écriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompteRestService.class);
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES :</b>
	 * <br/>Les traitements métier relatifs à l'entité 'Compte'.
	 */
	@Autowired
	private ICompteMetier compteMetier;

	
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Ajouter ou modifier un compte dans les données persistantes.
	 * 
	 * @param pCompte Le compte avec lequel l'opération doit être effectuée.
	 * @return Compte Le compte ajouté.
	 * @throws InvalidArgumentException Lancée dans le cas : Le compte fourni est invalide.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération demandée est illégale.
	 * @see afpa.cdi.banque.metier.ICompteMetier#addOrUpdate(afpa.cdi.banque.model.Compte)
	 */
	@RequestMapping(value="/compte", method=RequestMethod.POST)
	@ResponseBody
	public Compte add(@RequestBody Compte pCompte) throws InvalidArgumentException, IllegalOperationException {
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : add -- BEGIN");
		
		Compte compteCreated = compteMetier.addOrUpdate(pCompte);
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : add -- END");
		return compteCreated;
	}

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher un compte dans les données persistantes.
	 * <br/>Critère de recherche : Le code du compte.
	 * 
	 * @param pCode Le code du compte à rechercher.
	 * @return Compte Le compte trouvé.
	 * @see afpa.cdi.banque.metier.ICompteMetier#find(java.lang.String)
	 */
	@RequestMapping(value="/compte/{pCode}", method=RequestMethod.GET)
	public Compte find(@PathVariable String pCode) {
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : find -- BEGIN");

		Compte compteFound = compteMetier.find(pCode);
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : find -- END");
		return compteFound;
	}

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de comptes dans les données persistantes.
	 * <br/>Critère de recherche : Aucun (-> rechercher tous les comptes).
	 * 
	 * @return List<Compte> La collection des comptes trouvés.
	 * @see afpa.cdi.banque.metier.ICompteMetier#findAll()
	 */
	@RequestMapping(value="/comptes/all", method=RequestMethod.GET)
	public List<Compte> findAll() {
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : findAll -- BEGIN");

		List<Compte> comptesFound = compteMetier.findAll();
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : findAll -- END");
		return comptesFound;
	}

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de comptes dans les données persistantes.
	 * <br/>Critère de recherche : Le code du client associé au compte.
	 * 
	 * @param pClientCode Le code du client associé aux comptes à rechercher.
	 * @return List<Compte> La collection des comptes trouvés.
	 * @see afpa.cdi.banque.metier.ICompteMetier#findByClient(java.lang.String)
	 */
	@RequestMapping(value="/comptes", method=RequestMethod.GET)
	public List<Compte> findByClient(@RequestParam(value="clientCode", required=true) Long pClientCode) {
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : findByClient -- BEGIN");

		List<Compte> comptesFound = compteMetier.findByClient(pClientCode);
		
		LOGGER.info("CLASS : CompteRestService -- METHOD : findByClient -- END");
		return comptesFound;
	}
}
