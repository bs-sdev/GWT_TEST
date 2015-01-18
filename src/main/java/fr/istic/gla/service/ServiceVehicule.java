package fr.istic.gla.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.model.Vehicule;
import fr.istic.gla.ressource.RessourceVehicule;

@Path("/vehicule")
public class ServiceVehicule {
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Vehicule trouverParId(@PathParam("id") int id){
		RessourceVehicule rsV = new RessourceVehicule();
		
		return rsV.trouverParId(id);
	}
	
	@Path("/ajoutTest")
	public Vehicule ajoutTestVehicule(){
		RessourceVehicule rsV = new RessourceVehicule();
		
		return rsV.ajoutTestVehicule();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Vehicule> listerTout() {
		RessourceVehicule rsV = new RessourceVehicule();
		
		return rsV.listerTout();
	}
	
	@DELETE
	@Path("supprimer/{id}")
	//@Produces({ MediaType.APPLICATION_JSON })
	public Vehicule supprimerVehicule(@PathParam("id") int id) {
		RessourceVehicule rsV = new RessourceVehicule();
		
		return rsV.supprimerVehicule(id);
	}
}
