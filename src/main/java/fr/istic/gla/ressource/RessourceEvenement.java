package fr.istic.gla.ressource;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import fr.istic.gla.model.Evenement;

public class RessourceEvenement extends Ressource {
	
	public RessourceEvenement(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
	}
	
	public RessourceEvenement(EntityManager manager) {
		super(manager);
	}
	
	public Evenement trouverParId(int id){
		return manager.find(Evenement.class, id);
	}
	
	public List<Evenement> listerTout() {
		return manager.createQuery("select e FROM Evenement e").getResultList();
	}
	
	public Evenement ajoutTestEvenement(){
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Date myDate = new Date(2015, 3, 8);
		Evenement evenement = new Evenement("testEvt", null);
		System.out.println("Evenement crée => " + evenement);
		manager.merge(evenement);
		transaction.commit();
		return evenement;
	}
	
	// Ne fonctionne pas ...
	public Evenement supprimerEvenement(int id) {
		EntityTransaction eTransaction = manager.getTransaction();
		eTransaction.begin();
		Evenement evenementToRemove = manager.find(Evenement.class, id);
		manager.remove(evenementToRemove);
		eTransaction.commit();
		return evenementToRemove;
	}
	
	
}
