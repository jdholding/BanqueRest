/**
 * 
 */
package afpa.cdi.banque.metier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.cdi.banque.dao.IEmployeRepository;
import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.model.Employe;

/**
 * @author 94010-24-03
 *
 */
@Service
public class EmployeMetierImpl implements IEmployeMetier {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeMetierImpl.class);
	
	@Autowired
	private IEmployeRepository employeRepository; 
	

	@Override
	public Employe addOrUpdate(Employe pEmploye) throws InvalidArgumentException, IllegalOperationException {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : addOrUpdate -- BEGIN");
		
		checkValidity(pEmploye);
		
			if(pEmploye.getCode()==null){
				Employe employeCreated = add(pEmploye);
				
				LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : addOrUpdate -- END");
				return employeCreated;
				
			} else {
				Employe employeUpdated = update(pEmploye);
				
				LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : addOrUpdate -- END");
				return employeUpdated;
			}
	}

	@Override
	public Employe find(Long pCode) {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : find -- BEGIN");
		
		Employe employeFound = employeRepository.findOne(pCode);
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : find -- END");
		return employeFound;
	}

	@Override
	public void delete(Long pCode) {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : delete -- BEGIN");
		
		employeRepository.delete(pCode);		
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : delete -- END");
	}

	@Override
	public List<Employe> findByNom(String pNom) {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findByNom -- BEGIN");
		
		List<Employe> employesFound = this.employeRepository.findByNom(pNom);
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findByNom -- END");
		return employesFound;
	}

	@Override
	public List<Employe> findAll() {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findAll -- BEGIN");
		
		List<Employe> employesFound = this.employeRepository.findAll();
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findAll -- END");
		return employesFound;
	}
	

	@Override
	public Employe findByCompte(String pCompteCode) {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findByCompte -- BEGIN");
		
		Employe employeFound = this.employeRepository.findByCompte(pCompteCode);
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findByCompte -- END");
		return employeFound;
	}

	@Override
	public Employe findByOperation(Long pOperationNumber) {
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findByOperation -- BEGIN");
		
		Employe employeFound = this.employeRepository.findByOperation(pOperationNumber);
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : findByOperation -- END");
		return employeFound;
	}
	
	
	
	
	
	
	
	
	
	private Employe add(Employe pEmploye){
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : add -- BEGIN");
		
		Employe employeCreated = this.employeRepository.save(pEmploye);
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : add -- END");
		return employeCreated;
	}
	
	private Employe update(Employe pEmploye) throws IllegalOperationException{
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : update -- BEGIN");
		
		checkLegalityOfUpdate(pEmploye);
		
		Employe employeUpdated = this.employeRepository.save(pEmploye);
		
		LOGGER.info("CLASS : EmployeMetierImpl -- METHOD : update -- END");
		return employeUpdated;
	}
	
	
	
	
	
	private boolean checkLegalityOfUpdate(Employe pEmploye) throws IllegalOperationException{
		Employe employeFound = this.employeRepository.findOne(pEmploye.getCode());
		
		if(employeFound == null){
			LOGGER.error("L'employe -- Valeur : null");
			throw new IllegalOperationException("->L'employe -- valeur : null");
		}
		return true;
	}
	
	private boolean checkValidity(Employe pEmploye) throws InvalidArgumentException {
		if (pEmploye == null){
			throw new InvalidArgumentException("->L'employe -- Valeur : null");
		}
		if (pEmploye.getNom() == null){
			LOGGER.error("L'employe -- Valeur : null");
			throw new InvalidArgumentException("Le nom de l'employe -- Valeur : null");
		}
		return true;
	}


}
