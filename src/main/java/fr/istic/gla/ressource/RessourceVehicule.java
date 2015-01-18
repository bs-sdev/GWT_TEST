package fr.istic.gla.ressource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.gla.model.Vehicule;

public class RessourceVehicule extends Ressource{

	public RessourceVehicule() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
	}
	
	public RessourceVehicule(EntityManager manager) {
		super(manager);
	}
	
	public Vehicule trouverParId(int id) {
		return manager.find(Vehicule.class, id);
	}
	
	public List<Vehicule> listerTout() {
		return manager.createQuery("select e FROM Vehicule e").getResultList();
	}
	
	public Vehicule ajoutTestVehicule() {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Vehicule vehicule = new Vehicule("marque", "modele", 4, null, null);
		manager.merge(vehicule);
		transaction.commit();
		return vehicule;
	}

	public void ajouterVehicule(final Vehicule vehicule) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(vehicule);
		transaction.commit();
	}

	// Ne fonctionne pas ...
	public Vehicule supprimerVehicule(int id) {
		EntityTransaction eTransaction = manager.getTransaction();
		eTransaction.begin();
		Vehicule vehiculeToRemove = manager.find(Vehicule.class, id);
		manager.remove(vehiculeToRemove);
		eTransaction.commit();
		return vehiculeToRemove;
	}
	
}
