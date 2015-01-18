package fr.istic.gla.ressource;

import javax.persistence.EntityManager;

public abstract class Ressource {

	protected EntityManager manager;
	
	public Ressource(EntityManager manager) {
		this.manager = manager;
	}
	public Ressource() {
		// TODO Auto-generated constructor stub
	}
}
