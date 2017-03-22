/**
 * 
 */
package afpa.cdi.banque.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import afpa.cdi.banque.exception.IllegalOperationException;
import afpa.cdi.banque.exception.InvalidArgumentException;
import afpa.cdi.banque.metier.IEmployeMetier;
import afpa.cdi.banque.model.Employe;

/**
 * @author 94010-24-03
 *
 */
@RestController
public class EmployeRestService {
	
	

	/**
	 * <b>OBJET QUI POSSEDE LES FONCTIONNALITES SUIVANTES : </b>
	 * <br/>Les fonctionnalités d'écriture de messages de log dans la console.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeRestService.class);

	/**
	 * <b>Objet qui possede les traitment metier relatifs a l'entite 'Employe'<b/>
	 */
	@Autowired
	private IEmployeMetier employeMetier;
	
	
	@RequestMapping(value="/employe", method=RequestMethod.POST)
	@ResponseBody
	public Employe add(@RequestBody Employe pEmploye) throws InvalidArgumentException, IllegalOperationException {
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : add -- BEGIN");

		Employe employeCreated = employeMetier.addOrUpdate(pEmploye);
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : add -- END");
		return employeCreated;		
	}
	
	
	@RequestMapping(value="/employe/{pCode}", method=RequestMethod.GET)
	public Employe find(@PathVariable Long pCode){
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : find -- BEGIN");
		
		Employe employeFound = employeMetier.find(pCode);
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : find -- END");
		return employeFound;		
	}
	
	@RequestMapping(value="/employes/all", method=RequestMethod.GET)
	public List<Employe> findAll(){
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findAll -- BEGIN");
		
		List<Employe> employesFound = employeMetier.findAll();
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findAll -- END");
		return employesFound;
	}
	
	@RequestMapping(value="/employes", method=RequestMethod.GET)
	public List<Employe> findByNom(@RequestParam(value="nom", required=true) String pNom){
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findByNom -- BEGIN");
		
		List<Employe> employesFound = employeMetier.findByNom(pNom);
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findByNom -- END");
		return employesFound;
	}
	
	@RequestMapping(value="/employe/compte", method=RequestMethod.GET)
	public Employe findByCompte(@RequestParam(value="compteCode", required=true) String pCompteCode){
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findByCompte -- BEGIN");
		
		Employe employeFound = employeMetier.findByCompte(pCompteCode);
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findByCompte -- END");
		return employeFound;	
	}
	
	@RequestMapping(value="/employe/operation", method=RequestMethod.GET)
	public Employe findByOperation(@RequestParam(value="operationNumero", required=true) Long pOperationNumero){
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findByOperation -- BEGIN");
		
		Employe employeFound = employeMetier.findByOperation(pOperationNumero);
		
		LOGGER.info("CLASS : EmployeRestService -- METHOD : findByOperation -- END");
		return employeFound;

	}
}
