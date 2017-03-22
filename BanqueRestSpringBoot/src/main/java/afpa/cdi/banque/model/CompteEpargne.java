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
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

	
	private double taux;
	
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public CompteEpargne() { super(); }

	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 *
	 * @param pCode
	 * @param pDateCreation
	 * @param pSolde
	 * @param pTaux
	 */
	public CompteEpargne(String pCode, Date pDateCreation, double pSolde, double pTaux) {
		super(pCode, pDateCreation, pSolde);
		this.taux = pTaux;
	}



	/**
	 * @return the taux
	 */
	public double getTaux() { return this.taux; }

	/**
	 * @param pTaux the taux to set
	 */
	public void setTaux(double pTaux) { this.taux = pTaux; }
}
