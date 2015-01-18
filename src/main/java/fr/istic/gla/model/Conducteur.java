/**
 * 
 */
package fr.istic.gla.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author benjamin
 *
 */
@Entity
@Table(name="conducteur")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id") // pour eviter la double récursivité
public class Conducteur  {
	
	@Id
	@GeneratedValue
	protected int id;
	
	protected String nom;
	
	protected String prenom;
	
	protected Date dateNaissance;
	
	@OneToMany(fetch=FetchType.EAGER,
			cascade = CascadeType.ALL,
			mappedBy="conducteur")
	protected List<Covoiturage> covoiturages;

	protected String mail;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="CONDUCTEUR_ID")
	protected Vehicule vehicule;
	
	public Conducteur() {}

	/**
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param vehicule 
	 */
	public Conducteur(String nom, String prenom, Date dateNaissance, String mail, Vehicule vehicule) 
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.mail = mail;
		this.covoiturages = new ArrayList<Covoiturage>();
		this.vehicule = vehicule;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the covoiturages
	 */
	@OneToMany
	@JoinColumn(name="COVOITURAGE_id")
	public List<Covoiturage> getCovoiturages() {
		return covoiturages;
	}

	/**
	 * @param covoiturages the covoiturages to set
	 */
	public void setCovoiturages(List<Covoiturage> covoiturages) {
		this.covoiturages = covoiturages;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the vehicule
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}

	/**
	 * @param vehicule the vehicule to set
	 */
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}	
	
	
}
