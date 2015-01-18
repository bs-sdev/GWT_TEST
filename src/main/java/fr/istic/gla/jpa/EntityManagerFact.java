package fr.istic.gla.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFact {

	EntityManagerFactory factory ;
	
	static EntityManagerFact inst= new EntityManagerFact();
	
	/**
	 * @return the factory
	 */
	public EntityManagerFactory getFactory() {
		return factory;
	}

	/**
	 * @return the inst
	 */
	public static EntityManagerFact getInst() {
		return inst;
	}

	private EntityManagerFact() {
		 factory = Persistence.createEntityManagerFactory("dev");
	}
}
