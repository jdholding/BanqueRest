/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.cdi.banque.dao.ICompteRepository;
import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.model.Compte;

/**
 * @author 1603599
 *
 * <br/><b>CLASSE QUI IMPLEMENTE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les traitements métier relatifs à l'entité 'Compte'.
 */
@Service
public class CompteMetierImpl implements ICompteMetier {

	
	
	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b>
	 * <br/>Les fonctionnalités d'écriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompteMetierImpl.class);

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES :</b>
	 * <br/>Les fonctionnalités de persistance relatives à l'entité 'Compte'.
	 */
	@Autowired
	private ICompteRepository compteRepository;
	

	
	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.ICompteMetier#addOrUpdate(afpa.cdi.banque.model.Compte)
	 */
	@Override
	public Compte addOrUpdate(Compte pCompte) throws InvalidArgumentException, IllegalOperationException {
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : addOrUpdate -- BEGIN");

		///////////////////////////////////////////////////////////////////////
		// (01.)TRAITER LE CAS D'ERREUR SUIVANT :
		//      ->LE COMPTE FOURNI (EN PARAMETRE) EST INVALIDE.
		///////////////////////////////////////////////////////////////////////
		checkValidity(pCompte);
		
		///////////////////////////////////////////////////////////////////////
		// (02.)RECHERCHER L'OBJET 'COMPTE' (DANS LES DONNEES PERSISTANTES) :
		//      ->CRITERE DE RECHERCHE : LE CODE DU COMPTE.
		//      ->VALEUR UTILISEE : LE CODE DU COMPTE FOURNI.
		///////////////////////////////////////////////////////////////////////
		Compte compteFound = this.compteRepository.findOne(pCompte.getCode());

		///////////////////////////////////////////////////////////////////////
		// (03.)TRAITER LES CAS SUIVANTS :
		//      ->(03.01.)LE COMPTE FOURNI N'EXISTE PAS (DANS LES DONNEES PERSISTANTES).
		//      ->(03.02.)LE COMPTE FOURNI EXISTE DEJA (DANS LES DONNEES PERSISTANTES).
		///////////////////////////////////////////////////////////////////////
		if (compteFound == null) {
			///////////////////////////////////////////////////////////////////////
			// (03.01.)LE COMPTE FOURNI N'EXISTE PAS (DANS LES DONNEES PERSISTANTES).
			//         ->EFFECTUER L'OPERATION SUIVANTE : 
			//             AJOUTER LE COMPTE DANS LES DONNEES PERSISTANTES.
			///////////////////////////////////////////////////////////////////////
			Compte compteCreated = add(pCompte);
			
			LOGGER.info("CLASS : CompteMetierImpl -- METHOD : addOrUpdate -- END");
			return compteCreated;
			
		} else {
			///////////////////////////////////////////////////////////////////////
			// (03.02.)LE COMPTE FOURNI EXISTE DEJA (DANS LES DONNEES PERSISTANTES).
			//         ->EFFECTUER L'OPERATION SUIVANTE : 
			//             MODIFIER LE COMPTE DANS LES DONNEES PERSISTANTES.
			///////////////////////////////////////////////////////////////////////
			Compte compteUpdated = update(pCompte, compteFound); 
			
			LOGGER.info("CLASS : CompteMetierImpl -- METHOD : addOrUpdate -- END");
			return compteUpdated;
		}
	}

	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.ICompteMetier#find(java.lang.String)
	 */
	@Override
	public Compte find(String pCode) {
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : find -- BEGIN");
		
		Compte compteFound = compteRepository.findOne(pCode);
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : find -- END");
		return compteFound;
	}

	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.ICompteMetier#findAll()
	 */
	@Override
	public List<Compte> findAll() {
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : findAll -- BEGIN");
		
		List<Compte> comptesFound = this.compteRepository.findAll();
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : findAll -- END");
		return comptesFound;
	}

	/* (non-Javadoc)
	 * @see afpa.cdi.banque.metier.ICompteMetier#findByClient(java.lang.String)
	 */
	@Override
	public List<Compte> findByClient(Long pClientCode) {
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : findByClient -- BEGIN");
		
		List<Compte> comptesFound = this.compteRepository.findByClientCode(pClientCode);
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : findByClient -- END");
		return comptesFound;
	}
	
	
	
	/**
	 * <b>METHODE QUI EFFECTUE L'OPERATION SUIVANTE :</b>
	 * <br/>Ajouter un compte dans les données persistantes :
	 * 
	 * @param pCompte Le compte fourni (avec lequel l'ajout doit être effectué).
	 * @return Compte Le compte ajouté.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération est illégale.
	 */
	private Compte add(Compte pCompte) throws IllegalOperationException {

		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : add -- BEGIN");
		
		///////////////////////////////////////////////////////////////////////
		// (01.)VERIFIER LA LEGALITE DE L'OPERATION SUIVANTE :
		//      ->L'AJOUT DU COMPTE FOURNI.
		///////////////////////////////////////////////////////////////////////
		checkLegalityOfAdd(pCompte);
		
		///////////////////////////////////////////////////////////////////////
		// (02.)ALIMENTER L'OBJET 'COMPTE' FOURNI :
		//      ->ATTRIBUT A ALIMENTER : LA DATE DE CREATION.
		//      ->VALEUR A AFFECTER    : LA DATE SYSTEME.
		///////////////////////////////////////////////////////////////////////
		pCompte.setDateCreation(new Date());

		///////////////////////////////////////////////////////////////////////
		// (03.)EFFECTUER L'OPERATION SUIVANTE :
		//      ->SAUVEGARDER LE COMPTE DANS LES DONNEES PERSISTANTES.
		///////////////////////////////////////////////////////////////////////
		Compte compteCreated = this.compteRepository.save(pCompte);
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : add -- END");
		return compteCreated;
	}
	
	/**
	 * <b>METHODE QUI EFFECTUE L'OPERATION SUIVANTE :</b>
	 * <br/>Modifier un compte dans les données persistantes :
	 * 
	 * @param pCompteProvided Le compte fourni (avec lequel la modification doit être effectuée).
	 * @param pCompteExisting Le compte existant déjà (avec lequel la modification doit être effectuée). 
	 * @return Compte Le compte modifié.
	 */
	private Compte update(Compte pCompteProvided, Compte pCompteExisting) {
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : update -- BEGIN");

		///////////////////////////////////////////////////////////////////////
		// (01.)EFFECTUER L'OPERATION SUIVANTE :
		//      ->MODIFIER LE COMPTE EXISTANT A L'AIDE DU COMPTE FOURNI.
		///////////////////////////////////////////////////////////////////////
		pCompteExisting.setSolde(pCompteProvided.getSolde());
		
		///////////////////////////////////////////////////////////////////////
		// (02.)EFFECTUER L'OPERATION SUIVANTE :
		//      ->SAUVEGARDER LE COMPTE MODIFIE DANS LES DONNEES PERSISTANTES.
		///////////////////////////////////////////////////////////////////////
		Compte compteUpdated = this.compteRepository.save(pCompteExisting);
		
		LOGGER.info("CLASS : CompteMetierImpl -- METHOD : update -- END");
		return compteUpdated;
	}
	
	
	
	/**
	 * <b>METHODE QUI EFFECTUE LA VERIFICATION SUIVANTE :</b>
	 * <br/>Vérifier la légalité de l'opération suivante :
	 * <br/>L'ajout d'un compte dans les données persistances.
	 * 
	 * @param pCompte Le compte fourni (avec lequel l'ajout doit être effectué).
	 * @return boolean 
	 *         <br/>VRAI : Cas où l'opération est légale. 
	 *         <br/>FAUX : Cas où l'opération est illégale.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération est illégale.
	 */
	private boolean checkLegalityOfAdd(Compte pCompte) throws IllegalOperationException {
		
		///////////////////////////////////////////////////////////////////////
		// (01.)TRAITER LE CAS D'ERREUR SUIVANT :
		//      ->DANS LE COMPTE FOURNI, L'ATTRIBUT 'CLIENT' EST NULL.
		///////////////////////////////////////////////////////////////////////
		if (pCompte.getClient() == null) {
			LOGGER.error("Le client associe au compte -- Valeur : null");
			throw new IllegalOperationException("->Le client associe au compte -- Valeur : null");
		}
		///////////////////////////////////////////////////////////////////////
		// (02.)TRAITER LE CAS D'ERREUR SUIVANT :
		//      ->DANS LE COMPTE FOURNI, L'ATTRIBUT 'CLIENT' POSSEDE UN CODE NULL.
		///////////////////////////////////////////////////////////////////////
		if (pCompte.getClient().getCode() == null) {
			LOGGER.error("Le code du client associe au compte -- Valeur : null");
			throw new IllegalOperationException("->Le code du client associe au compte -- Valeur : null");
		}
		return true;
	}
	
	/**
	 * <b>METHODE QUI EFFECTUE LA VERIFICATION SUIVANTE :</b>
	 * <br/>Vérifier la validité d'un compte.
	 * 
	 * @param pCompte Le compte à valider.
	 * @return boolean 
	 *         <br/>VRAI : Cas où le compte est valide. 
	 *         <br/>FAUX : Cas où le compte est invalide.
	 * @throws InvalidArgumentException Lancée dans le cas : Le compte est invalide.
	 */
	private boolean checkValidity(Compte pCompte) throws InvalidArgumentException {
		
		///////////////////////////////////////////////////////////////////////
		// (01.)TRAITER LE CAS D'ERREUR SUIVANT :
		//      ->LE COMPTE FOURNI EST NULL.
		///////////////////////////////////////////////////////////////////////
		if (pCompte == null) {
			LOGGER.error("Le compte -- Valeur : null");
			throw new InvalidArgumentException("->Le compte -- Valeur : null");
		}
		///////////////////////////////////////////////////////////////////////
		// (02.)TRAITER LE CAS D'ERREUR SUIVANT :
		//      ->DANS LE COMPTE FOURNI, L'ATTRIBUT 'CODE' EST NULL.
		///////////////////////////////////////////////////////////////////////
		if (pCompte.getCode() == null) {
			LOGGER.error("Le code du compte -- Valeur : null");
			throw new InvalidArgumentException("->Le code du compte -- Valeur : null");
		}
		///////////////////////////////////////////////////////////////////////
		// (03.)TRAITER LE CAS SUIVANT :
		//      ->DANS LE COMPTE FOURNI, L'ATTRIBUT 'CODE' EST NON NULL.
		///////////////////////////////////////////////////////////////////////
		return true;
	}
}
