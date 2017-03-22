/**
 * 
 */
package afpa.cdi.banque.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 1603599
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public Versement() {
		super();
	}

	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 * 
	 * @param pDate
	 * @param pMontant
	 */
	public Versement(Date pDate, double pMontant) {
		super(pDate, pMontant);
	}
}
