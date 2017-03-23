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

import afpa.cdi.banque.dao.IOperationRepository;
import afpa.cdi.banque.model.Operation;

/**
 * @author 94010-24-03
 *
 */
@Service
public class OperationMetierImpl implements IOperationMetier{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationMetierImpl.class);
	
	@Autowired
	private IOperationRepository operationRepository;
	

	@Override
	public Operation add(Operation pOperation) {
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : add -- BEGIN");
		
		pOperation.setDate(new Date());
		
		Operation operationCreated = operationRepository.save(pOperation);
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : add -- END");
		return operationCreated;
	}

	@Override
	public Operation find(Long pOperationNumero) {
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : find -- BEGIN");
		
		Operation operationFound = operationRepository.findOne(pOperationNumero);
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : find -- END");
		return operationFound;
	}

	@Override
	public List<Operation> findByCompte(String pCompteCode) {
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : findByCompte -- BEGIN");
		
		List<Operation> operationsFound = operationRepository.findByCompte(pCompteCode);
	
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : findByCompte -- END");
		return operationsFound;
	}

	@Override
	public List<Operation> findByDate(Date pDate) {
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : findByDate -- BEGIN");
		
		List<Operation> operationsFound = operationRepository.findByDate(pDate);
		
		LOGGER.info("CLASS : OperationMetierImpl -- METHOD : findByDate -- END");
		return operationsFound;
	}

}
