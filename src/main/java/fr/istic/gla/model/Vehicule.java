package fr.istic.gla.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="vehicule")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id") // pour eviter la double récursivité
public class Vehicule {
	@Id
	@GeneratedValue
	@Column(name="id")
	protected int id;
	
	protected String marque;
	
	protected String modele;
	
	protected int nbPlace;
	
	@OneToOne(cascade = {CascadeType.ALL})
	protected Conducteur conducteur;
	
	@OneToOne(cascade = {CascadeType.ALL})
	protected Covoiturage covoiturage;
	
	public Vehicule(){
		super();
	}

	/**
	 * @param marque
	 * @param modele
	 * @param nbPlace
	 * @param conducteur
	 * @param covoiturage
	 */
	public Vehicule(String marque, String modele, int nbPlace,
			Conducteur conducteur, Covoiturage covoiturage) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.nbPlace = nbPlace;
		this.conducteur = conducteur;
		this.covoiturage = covoiturage;
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
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @return the nbPlace
	 */
	public int getNbPlace() {
		return nbPlace;
	}

	/**
	 * @param nbPlace the nbPlace to set
	 */
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	/**
	 * @return the conducteur
	 */
	public Conducteur getConducteur() {
		return conducteur;
	}

	/**
	 * @param conducteur the conducteur to set
	 */
	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	/**
	 * @return the covoiturage
	 */
	public Covoiturage getCovoiturage() {
		return covoiturage;
	}

	/**
	 * @param covoiturage the covoiturage to set
	 */
	public void setCovoiturage(Covoiturage covoiturage) {
		this.covoiturage = covoiturage;
	}
	
	

}
