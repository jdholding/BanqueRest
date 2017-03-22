/**
 * 
 */
package afpa.cdi.banque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import afpa.cdi.banque.model.Employe;

/**
 * @author 94010-24-03
 *Interface qui expose les fonctionalites de persistance pour l'entitie Employe
 *
 */
public interface IEmployeRepository extends JpaRepository<Employe, Long> {

	@Query("select e from Employe e where e.nom like :xNom")
	public List<Employe> findByNom(@Param("xNom")String pNom);
	
	@Query("select c.employe from Compte c where c.code = :xCompteCode")
	public Employe findByCompte(@Param("xCompteCode")String pCompteCode);

	@Query("select o.employe from Operation o where (o.numero=:xOperationNumero)")
	public Employe findByOperation(@Param("xOperationNumero")Long pOperationNumero);
}
