package fr.istic.gla.main;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		
		// Data

	
		// Manager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager em = factory.createEntityManager();
		
//		em.getTransaction().begin();
//		em.persist(conducteur);
//		em.getTransaction().commit();
//		
		// Ressources
		/*RessourceConducteur rscConducteur = new RessourceConducteur(manager);
		RessourceCovoiturage rscCovoiturage = new RessourceCovoiturage(manager);

		conducteur.getCovoiturages().add(covoiturage);
		covoiturage.setConducteur(conducteur);
		rscConducteur.ajouterConducteur(conducteur);
		//rscCovoiturage.ajouterCovoiturage(covoiturage);
		/*rscUtilisateur.ajouterUtilisateur(RogerDURANT);
		rscUtilisateur.ajouterUtilisateur(EdouardBRACAME);
		rscUtilisateur.ajouterUtilisateur(FrancoisHOLLANDE);
		rscUtilisateur.ajouterUtilisateur(ValentinoROSSI);
		rscVehicule.ajouterVehicule(vehicule);
		rscEvenement.ajouterEvenement(evenement);
		rscCommentaire.ajouterCommentaire(commentaire);*/
		
	
	}

}
