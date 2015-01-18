package fr.istic.gla.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.model.Evenement;
import fr.istic.gla.ressource.RessourceEvenement;

@Path("/evenement")
public class ServiceEvenement {
	
	
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
    public Evenement trouverParId(@PathParam("id") int id){
		RessourceEvenement rsC = new RessourceEvenement();
		
		return rsC.trouverParId(id);
	}
	
	@Path("/ajoutTest")
	public Evenement ajoutTestEvenement(){
		RessourceEvenement rsE = new RessourceEvenement();
		
		return rsE.ajoutTestEvenement();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Evenement> listerTout() {
		RessourceEvenement rsE = new RessourceEvenement();
		
		return rsE.listerTout();
	}	
	
	@DELETE
	@Path("supprimer/{id}")
	public Evenement supprimerEvenement(@PathParam("id") int id) {
		RessourceEvenement rsC = new RessourceEvenement();
		
		return rsC.supprimerEvenement(id);
	}
}
