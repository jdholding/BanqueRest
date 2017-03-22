package afpa.cdi.banque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 1603599
 * 
 * <b>EXCEPTION LANCEE DANS LE CAS SUIVANT :</b>
 * <br/><b>L'OPERATION DEMANDEE EST ILLEGALE</b>
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Operation illégale !")
public class IllegalOperationException extends Exception {

	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * <b>LE MESSAGE FOURNISSANT DES INFORMATIONS SUR L'EXCEPTION.</b>
	 */
	private String message;
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS.</b>
	 */
	public IllegalOperationException () { super(); }
	
	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS.</b>
	 * 
	 * @param pMessage Le message
	 */
	public IllegalOperationException (String pMessage) { super(pMessage); }
	
	/**
	 * <b>GETTER SUR LE MESSAGE</b>
	 * @return String Le message.
	 */
	public String getMessage() { return this.message; }
}
