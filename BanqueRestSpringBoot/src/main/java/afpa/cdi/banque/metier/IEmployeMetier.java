/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.model.Employe;

/**
 * @author 94010-24-03
 *Interface qui expose les traitments metiers a l'entitie 'Employe'
 *
 */
public interface IEmployeMetier {
	
	public Employe addOrUpdate(Employe pEmploye) throws InvalidArgumentException, IllegalOperationException;
	
	public Employe find(Long pCode);
	
	public void delete(Long pCode);
	
	public List<Employe> findByNom(String pNom);
	
	public Employe findByCompte(String pCompteCode);
	
	public Employe findByOperation(Long pOperation);
	
	public List<Employe> findAll();

}
