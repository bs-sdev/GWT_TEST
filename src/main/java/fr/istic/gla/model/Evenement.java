package fr.istic.gla.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "evenement")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id") // pour eviter la double récursivité
public class Evenement {
	@Id
	@GeneratedValue
	@Column(name="id")
	protected int id;
	
	protected String lieuEvt;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="evenement")
	protected List<Covoiturage> covoiturage;

	/**
	 * 
	 */
	public Evenement() {
		super();
	}

	/**
	 * @param id
	 * @param lieuEvt
	 * @param covoiturage
	 */
	public Evenement(String lieuEvt, List<Covoiturage> covoiturage) {
		super();
		this.lieuEvt = lieuEvt;
		this.covoiturage = new ArrayList<Covoiturage>();
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
	 * @return the lieuEvt
	 */
	public String getLieuEvt() {
		return lieuEvt;
	}

	/**
	 * @param lieuEvt the lieuEvt to set
	 */
	public void setLieuEvt(String lieuEvt) {
		this.lieuEvt = lieuEvt;
	}

	/**
	 * @return the covoiturage
	 */
	@OneToMany
	@JoinColumn(name="EVENEMENT_ID")
	public List<Covoiturage> getCovoiturage() {
		return covoiturage;
	}

	/**
	 * @param covoiturage the covoiturage to set
	 */
	public void setCovoiturage(List<Covoiturage> covoiturage) {
		this.covoiturage = covoiturage;
	}

}
