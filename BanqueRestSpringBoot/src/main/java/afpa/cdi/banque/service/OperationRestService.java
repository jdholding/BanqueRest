/**
 * 
 */
package afpa.cdi.banque.service;

import java.util.Date;
import java.util.List;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import afpa.cdi.banque.metier.IOperationMetier;
import afpa.cdi.banque.model.Operation;

/**
 * @author 94010-24-03
 *
 */
@RestController
public class OperationRestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationRestService.class);
	
	@Autowired
	private IOperationMetier operationMetier;
	
	@RequestMapping(value="/operation", method=RequestMethod.POST)
	@ResponseBody
	public Operation add(@RequestBody Operation pOperation){
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : add -- BEGIN");
		
		Operation operationCreated = operationMetier.add(pOperation);
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : add -- END");
		return operationCreated;		
	}
	
	@RequestMapping(value="/operation", method=RequestMethod.GET)
	public Operation find(@RequestParam(value="numero", required=true) Long pOperationNumero){
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : find -- BEGIN");
		
		Operation operationFound = operationMetier.find(pOperationNumero);
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : find -- END");
		return operationFound;
		
	}
	
	@RequestMapping(value="/compte-operations", method=RequestMethod.GET)
	public List<Operation> findByCompte(@RequestParam(value="compte", required=true)String pCompteCode){
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : findByCompte -- BEGIN");
		
		List<Operation> operationsFound = operationMetier.findByCompte(pCompteCode);
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : findByCompte -- END");
		return operationsFound;		
	}
	
	@RequestMapping(value="/operations/date", method=RequestMethod.GET)
	public List<Operation> findByDate(@RequestParam(value="date", required=true)Date pDate){
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : findByDate -- BEGIN");
		
		List<Operation> operationsFound = operationMetier.findByDate(pDate);
		
		LOGGER.info("CLASS : OperationRestService -- METHOD : findByDate -- END");
		return operationsFound;
	}
	
	
	
	
	

}
