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
@DiscriminatorValue("R")
public class Retrait extends Operation {

	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public Retrait() {
		super();
	}

	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 * 
	 * @param pDate
	 * @param pMontant
	 */
	public Retrait(Date pDate, double pMontant) {
		super(pDate, pMontant);
	}
}
