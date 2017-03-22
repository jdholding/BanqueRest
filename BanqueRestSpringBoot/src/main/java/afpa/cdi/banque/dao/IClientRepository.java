/**
 * 
 */
package afpa.cdi.banque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import afpa.cdi.banque.model.Client;

/**
 * @author 1603599
 * 
 * <br/><b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les fonctionnalités de persistance pour l'entité 'Client'.
 */
public interface IClientRepository extends JpaRepository<Client, Long> {

	@Query("select c from Client c where c.nom like :xNom")
	public List<Client> findByNom(@Param("xNom")String pNom);
	
}
