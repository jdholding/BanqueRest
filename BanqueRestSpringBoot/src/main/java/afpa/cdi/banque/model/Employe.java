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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author 1603599
 * 
 * <br/><b>ENTITE : 'EMPLOYE'</b>
 */
@SuppressWarnings("serial")
@Entity
public class Employe implements Serializable {

	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="CODE_SUPERIEUR")
	private Employe superieur;
	
	@ManyToMany
	@JoinTable(name="EMPLOYE_GROUPE")
	private Collection<Groupe> groupes;
	
	
	
	/**
	 * <b>CONSTRUCTEUR SANS ARGUMENTS</b>
	 */
	public Employe() { super(); }

	/**
	 * <b>CONSTRUCTEUR AVEC ARGUMENTS</b>
	 *
	 * @param pNom
	 */
	public Employe(String pNom) {
		super();
		this.nom = pNom;
	}

	
	
	public Long               getCode()      { return this.code;      }
	public String             getNom()       { return this.nom;       }
	public Employe            getSuperieur() { return this.superieur; }
	public Collection<Groupe> getGroupes()   { return this.groupes;   }

	
	
	public void setCode     (Long               pCode     ) { this.code      = pCode;      }
	public void setNom      (String             pNom      ) { this.nom       = pNom;       }
	public void setSuperieur(Employe            pSuperieur) { this.superieur = pSuperieur; }
	public void setGroupes  (Collection<Groupe> pGroupes  ) { this.groupes   = pGroupes;   }
}
