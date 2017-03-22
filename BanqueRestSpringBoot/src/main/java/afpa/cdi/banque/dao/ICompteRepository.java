/**
 * 
 */
package afpa.cdi.banque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import afpa.cdi.banque.model.Compte;

/**
 * @author 1603599
 *
 * <br/><b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les fonctionnalités de persistance pour l'entité 'Compte'.
*/
public interface ICompteRepository extends JpaRepository<Compte, String> {

	@Query("select c from Compte c where c.client.code = :xClientCode")
	public List<Compte> findByClientCode(@Param("xClientCode")Long pClientCode);
}
