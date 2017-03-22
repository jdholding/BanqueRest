/**
 * 
 */
package afpa.cdi.banque.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author 1603599
 * 
 * <br/><b>ENTITE : 'GROUPE'</b>
 */
@SuppressWarnings("serial")
@Entity
public class Groupe implements Serializable {

	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	
	private String nom;
	
	@ManyToMany(mappedBy="groupes")
	private Collection<Employe> employes;

	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public Groupe() { super(); }

	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 *
	 * @param pNom
	 */
	public Groupe(String nom) {
		super();
		this.nom = nom;
	}
	
	

	public Long                getCode()     { return this.code;     }
	public String              getNom()      { return this.nom;      }
	public Collection<Employe> getEmployes() { return this.employes; }

	
	
	public void setCode    (Long                pCode    ) { this.code     = pCode;     }
	public void setNom     (String              pNom     ) { this.nom      = pNom;      }
	public void setEmployes(Collection<Employe> pEmployes) { this.employes = pEmployes; }
}
