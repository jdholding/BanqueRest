/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.Date;
import java.util.List;

import afpa.cdi.banque.model.Operation;

/**
 * @author 94010-24-03
 *
 */
public interface IOperationMetier {
	
	public Operation add(Operation pOperation);
	
	public Operation find(Long pOperationNumero);
	
	public List<Operation> findByCompte(String pCompteCode);
	
	public List<Operation> findByDate(Date pDate);
	

}
