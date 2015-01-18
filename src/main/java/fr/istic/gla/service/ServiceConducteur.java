package fr.istic.gla.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.ressource.RessourceConducteur;
import fr.istic.gla.model.Conducteur;

@Path("/conducteur")
public class ServiceConducteur {
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Conducteur trouverParId(@PathParam("id") int id){
		RessourceConducteur rsC = new RessourceConducteur();
		
		return rsC.trouverParId(id);
	}
	
	@Path("/ajoutTest")
	public Conducteur ajoutTestConducteur(){
		RessourceConducteur rsC = new RessourceConducteur();
		
		return rsC.ajoutTestConducteur();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Conducteur ajoutConducteur(Conducteur conducteur){
		RessourceConducteur rsC = new RessourceConducteur();
		
		return rsC.ajouterConducteur(conducteur);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Conducteur> listerTout() {
		RessourceConducteur rsC = new RessourceConducteur();
		
		return rsC.listerTout();
	}
	
	@DELETE
	@Path("supprimer/{id}")
	//@Produces({ MediaType.APPLICATION_JSON })
	public Conducteur supprimerConducteur(@PathParam("id") int id) {
		RessourceConducteur rsC = new RessourceConducteur();
		
		return rsC.supprimerConducteur(id);
	}
	
}
