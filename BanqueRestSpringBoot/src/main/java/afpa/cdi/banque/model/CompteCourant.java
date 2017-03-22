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
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	
	private double decouvert;
	
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public CompteCourant() { super(); }

	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 * 
	 * @param pCode
	 * @param pDateCreation
	 * @param pSolde
	 * @param pDecouvert
	 */
	public CompteCourant(String pCode, Date pDateCreation, double pSolde, double pDecouvert) {
		super(pCode, pDateCreation, pSolde);
		this.decouvert = pDecouvert;
	}

	

	/**
	 * @return the decouvert
	 */
	public double getDecouvert() { return this.decouvert; }

	/**
	 * @param pDecouvert the decouvert to set
	 */
	public void setDecouvert(double pDecouvert) { this.decouvert = pDecouvert; }
}
