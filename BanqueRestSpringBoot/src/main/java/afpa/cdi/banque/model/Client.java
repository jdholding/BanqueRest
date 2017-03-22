/**
 * 
 */
package afpa.cdi.banque.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 1603599
 * 
 * <br/><b>ENTITE : 'CLIENT'</b>
 */
@SuppressWarnings("serial")
@Entity
public class Client implements Serializable {

	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	
	private String nom;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Compte> comptes;
	
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public Client() { super(); }
	
	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 *
	 * @param pNom
	 */
	public Client(String pNom) {
		super();
		this.nom = pNom;
	}
	


	/**
	 * @return the code
	 */
	public Long getCode() { return this.code; }
	/**
	 * @return the nom
	 */
	public String getNom() { return this.nom; }
	/**
	 * @return the comptes
	 */
	@JsonIgnore
	public Collection<Compte> getComptes() { return this.comptes; }
	
	
	
	/**
	 * @param pCode the code to set
	 */
	public void setCode(Long pCode) { this.code = pCode; }
	/**
	 * @param pNom the nom to set
	 */
	public void setNom(String pNom) { this.nom = pNom; }
	/**
	 * @param pComptes the comptes to set
	 */
	public void setComptes(Collection<Compte> pComptes) { this.comptes = pComptes; }
}
