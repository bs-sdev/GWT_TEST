package fr.istic.gla.ressource;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.gla.model.Covoiturage;


public class RessourceCovoiturage extends Ressource {

	
	public RessourceCovoiturage() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
	}
	
	public RessourceCovoiturage(EntityManager manager) {
		super(manager);
	}
	
	public Covoiturage trouverParId(int id) {
		return manager.find(Covoiturage.class, id);
	}
	
	public List<Covoiturage> listerTout() {
		return manager.createQuery("select e FROM Covoiturage e").getResultList();
	}
	
	public Covoiturage ajoutTestCovoiturage(){
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Date myDate = new Date(2015, 3, 8);
		Covoiturage covoiturage = new Covoiturage("Rennes", myDate);
		System.out.println("Covoiturage crée => " + covoiturage);
		manager.merge(covoiturage);
		transaction.commit();
		return covoiturage;
	}

	public void ajouterCovoiturage(Covoiturage covoiturage) {
		EntityTransaction eTransaction = manager.getTransaction();
		eTransaction.begin();
		manager.merge(covoiturage);
		eTransaction.commit();
	}
	
	// Ne fonctionne pas ...
	public Covoiturage supprimerCovoiturage(int id) {
		EntityTransaction eTransaction = manager.getTransaction();
		eTransaction.begin();
		Covoiturage covoiturageToRemove = manager.find(Covoiturage.class, id);
		manager.remove(covoiturageToRemove);
		eTransaction.commit();
		return covoiturageToRemove;
	}

}
