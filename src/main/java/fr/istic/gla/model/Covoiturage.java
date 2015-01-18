/**
 * 
 */
package fr.istic.gla.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author benjamin
 *
 */
@Entity
@Table(name = "covoiturage")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id") // pour eviter la double récursivité
public class Covoiturage {
	
	@Id
	@GeneratedValue
	protected int id;
	
	protected String lieuDepart;
	
	protected Date dateDepart;
	
	@ManyToOne(fetch=FetchType.LAZY)
	protected Conducteur conducteur;
	
	@ManyToOne(fetch=FetchType.LAZY)
	protected Evenement evenement;
		
	public Covoiturage() {}

	public Covoiturage(String lieuDepart, Date dateDepart) {
		super();
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
	}

	/** @param lieuDepart the lieuDepart to set */
	public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }

	/** @return the id*/
	public int getId() { return id; }

	/** @param id the id to set */
	public void setId(int id) { this.id = id; }
	
	/** @return the dateDepart */
	public Date getDateDepart() { return dateDepart; }
	
	/** @param dateDepart the dateDepart to set */
	public void setDateDepart(Date dateDepart) { this.dateDepart = dateDepart; }
	
	public String getLieuDepart(){
		return lieuDepart;
	}

	/** @return the conducteur */
	@OneToOne
	public Conducteur getConducteur() { return conducteur; }

	/** @param conducteur the conducteur to set */
	public void setConducteur(Conducteur conducteur) { this.conducteur = conducteur; }
	
	/**
	 * @return the evenement
	 */
	@OneToOne
	public Evenement getEvenement() { return evenement; }

	/**
	 * @param evenement the evenement to set
	 */
	public void setEvenement(Evenement evenement) { this.evenement = evenement; }

	
	
	/*public void reserver(Covoitureur covoitureur) throws Exception {
		if (getPlacesRestantes() == 0) throw new Exception("Plus de place disponible.");
		if (covoitureur.equals(conducteur)) throw new Exception("Le conducteur fait parti du covoiturage.");
		covoitureurs.add(covoitureur);
	}*/

	/** @return the passagers */
	/*@OneToMany
	public List<Covoitureur> getCovoitureurs() { return covoitureurs; }*/

	/** @param covoitureurs the covoitureurs to set */
	//public void setCovoitureurs(List<Covoitureur> covoitureurs) { this.covoitureurs = covoitureurs; }


	/** @param covoitureurs the passagers to set */
	//public void setPassagers(List<Covoitureur> covoitureurs) { this.covoitureurs = covoitureurs; }


	/** @return the lieuDepart */



	/**
	 * @return the placesRestantes
	 */
	//@Transient
	//@JsonIgnore
	//public short getPlacesRestantes() { return (short) (conducteur.getVehicule().getNbPlace() - covoitureurs.size()); }


	/**
	 * @return the commentaires
	 */
	//@OneToMany
	//public List<Commentaire> getCommentaires() { return commentaires; }

	/**
	 * @param commentaires the commentaires to set
	 */
	/*public void setCommentaires(List<Commentaire> commentaires) {
		//this.commentaires = commentaires;
	}*/



	
}
