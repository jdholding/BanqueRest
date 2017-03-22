/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.List;

import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.model.Client;

/**
 * @author 1603599
 *
 * <br/><b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les traitements métier relatifs à l'entité 'Client'.
 */
public interface IClientMetier {

	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Ajouter ou modifier un client dans les données persistantes.
	 * 
	 * @param pClient Le client avec lequel l'opération doit être effectuée.
	 * @return Client Le client ajouté.
	 * @throws InvalidArgumentException Lancée dans le cas : Le client fourni est invalide.
	 * @throws IllegalOperationException Lancée dans le cas : L'opération demandée est illégale.
	 */
	public Client addOrUpdate(Client pClient) throws InvalidArgumentException, IllegalOperationException;
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher un client dans les données persistantes.
	 * <br/>Critère de recherche : Le code du client.
	 * 
	 * @param pCode Le code du client à rechercher.
	 * @return Client Le client trouvé.
	 */
	public Client find(Long pCode);
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de clients dans les données persistantes.
	 * <br/>Critère de recherche : Aucun (-> rechercher tous les clients).
	 * 
	 * @return List<Client> La collection des clients trouvés.
	 */
	public List<Client> findAll();
	
	/**
	 * <b>METHODE QUI EFFECTUE LES OPERATIONS SUIVANTES : </b>
	 * <br/>Rechercher une collection de clients dans les données persistantes.
	 * <br/>Critère de recherche : Le nom du client.
	 * 
	 * @param pNom Le nom du client à rechercher.
	 * @return List<Client> La collection des clients trouvés.
	 */
	public List<Client> findByNom(String pNom);
}
