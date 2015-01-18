package fr.istic.gla.ressource;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.gla.model.Conducteur;
import fr.istic.gla.model.Vehicule;

public class RessourceConducteur extends Ressource {


	public RessourceConducteur() {
//		manager = EntityManagerFact.getInst().getFactory().createEntityManager();
		
//		Il est peut être nécessaire de créer un singleton
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
	}

	public RessourceConducteur(EntityManager manager) {
		super(manager);
	}
	
	public Conducteur trouverParId(int id) {
		return manager.find(Conducteur.class, id);
	}
	
	public List<Conducteur> listerTout() {
		return manager.createQuery("select e FROM Conducteur e").getResultList();
	}
	
	public Conducteur ajoutTestConducteur(){
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Date myDate = new Date();
		Vehicule vehicule = new Vehicule("RENAULT", "Clio", 3, null, null);
		Conducteur conducteur = new Conducteur("LECOQ", "Maxime", myDate, "mail@fournisseur" , /*vehicule*/ null);
		vehicule.setConducteur(conducteur);
		manager.merge(conducteur);
		manager.merge(vehicule);
		transaction.commit();
		return conducteur;
	}

	public Conducteur ajouterConducteur(final Conducteur conducteur) {
		System.out.println("Test");
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		// VOIR si on peut checker la valeur de l'object pour ne pas en recréer trop à la volée ...
		manager.merge(conducteur);
		transaction.commit();
		
		return conducteur;
	}

	// Ne fonctionne pas ...
	public Conducteur supprimerConducteur(int id) {
		EntityTransaction eTransaction = manager.getTransaction();
		eTransaction.begin();
		Conducteur conducteurToRemove = manager.find(Conducteur.class, id);
		manager.remove(conducteurToRemove);
		eTransaction.commit();
		return conducteurToRemove;
	}
	
}
