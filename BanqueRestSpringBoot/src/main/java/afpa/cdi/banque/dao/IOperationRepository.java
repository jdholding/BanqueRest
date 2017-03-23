/**
 * 
 */
package afpa.cdi.banque.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import afpa.cdi.banque.model.Operation;

/**
 * @author 1603599
 *
 * <br/><b>INTERFACE QUI EXPOSE LES FONCTIONNALITES SUIVANTES : </b>
 * <br/>Les fonctionnalités de persistance pour l'entité 'Operation'.
 */
public interface IOperationRepository extends JpaRepository<Operation, Long> {

	@Query("select o from Operation o where o.compte.code = :xCompteCode")
	public List<Operation> findByCompte(@Param("xCompteCode")String pCompteCode);
	
	@Query("select o from Operation o where o.date = :xOperationDate")
	public List<Operation> findByDate(@Param("xOperationDate")Date pDate);
	
}
