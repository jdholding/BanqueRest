/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.List;

import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.model.Compte;

/**
 * @author 1603599
 *
 * <br/><b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les traitements métier relatifs à l'entité 'Compte'.
 */
public interface ICompteMetier {

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Ajouter ou modifier un compte dans les données persistantes.
	 * 
	 * @param pCompte Le compte avec lequel l'opération doit être effectuée.
	 * @return Compte Le compte ajouté.
	 * @throws InvalidArgumentException Lancée dans le cas : Le compte fourni est invalide.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération demandée est illégale.
	 */
	public Compte addOrUpdate(Compte pCompte) throws InvalidArgumentException, IllegalOperationException;
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher un compte dans les données persistantes.
	 * <br/>Critère de recherche : Le code du compte.
	 * 
	 * @param pCode Le code du compte à rechercher.
	 * @return Compte Le compte trouvé.
	 */
	public Compte find(String pCode);
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de comptes dans les données persistantes.
	 * <br/>Critère de recherche : Aucun (-> rechercher tous les comptes).
	 * 
	 * @return List<Compte> La collection des comptes trouvés.
	 */
	public List<Compte> findAll();
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de comptes dans les données persistantes.
	 * <br/>Critère de recherche : Le code du client associé au compte.
	 * 
	 * @param pClientCode Le code du client associé aux comptes à rechercher.
	 * @return List<Compte> La collection des comptes trouvés.
	 */
	public List<Compte> findByClient(Long pClientCode);
}
