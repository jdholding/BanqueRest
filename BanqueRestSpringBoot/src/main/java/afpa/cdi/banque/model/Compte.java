/**
 * 
 */
package afpa.cdi.banque.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author 1603599
 *
 * 
 * <br/><b>ENTITE : 'COMPTE'</b>
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_COMPTE", discriminatorType=DiscriminatorType.STRING, length=2)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(name="CC", value=CompteCourant.class),
	@Type(name="CE", value=CompteEpargne.class)
})
public abstract class Compte implements Serializable {

	
	
	@Id
	private String code;
	
	private Date dateCreation;
	private double solde;

	@ManyToOne
	@JoinColumn(name="CODE_CLIENT", nullable=false)
	private Client client;
	
	@OneToMany(mappedBy="compte", fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Operation> operations;
	
	@ManyToOne
	@JoinColumn(name="CODE_EMPLOYE", nullable=false)
	private Employe employe;
	
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public Compte() { super(); }
	
	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 *
	 * @param pCode
	 * @param pDateCreation
	 * @param pSolde
	 */
	public Compte(String pCode, Date pDateCreation, double pSolde) {
		super();
		this.code = pCode;
		this.dateCreation = pDateCreation;
		this.solde = pSolde;
	}



	/**
	 * @return the code
	 */
	public String getCode() { return this.code; }

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() { return this.dateCreation; }

	/**
	 * @return the solde
	 */
	public double getSolde() { return this.solde; }

	/**
	 * @return the client
	 */
	public Client getClient() { return this.client; }
	
	/**
	 * @return the operations
	 */
	@JsonIgnore
	public Collection<Operation> getOperations() { return this.operations; }

	
	public Employe getEmploye() {  return this.employe;  }
	
	
	/**
	 * @param pCode the code to set
	 */
	public void setCode(String pCode) { this.code = pCode; }

	/**
	 * @param pDateCreation the dateCreation to set
	 */
	public void setDateCreation(Date pDateCreation) { this.dateCreation = pDateCreation; }

	/**
	 * @param pSolde the solde to set
	 */
	public void setSolde(double pSolde) { this.solde = pSolde; }

	/**
	 * @param pClient the client to set
	 */
	public void setClient(Client pClient) { this.client = pClient; }

	/**
	 * @param pOperations the operations to set
	 */
	public void setOperations(Collection<Operation> pOperations) { this.operations = pOperations; }

	
	/**
	 * @param pEmploye the employe to set
	 */
	public void setEmploye(Employe pEmploye) {  this.employe = pEmploye; }
	
	
}
