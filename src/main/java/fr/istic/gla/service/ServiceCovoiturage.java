/**
 * 
 */
package fr.istic.gla.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.model.Covoiturage;
import fr.istic.gla.ressource.RessourceCovoiturage;

/**
 * @author ferdi
 *
 */

@Path("/covoiturage")
public class ServiceCovoiturage {

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
    public Covoiturage trouverParId(@PathParam("id") int id){
		RessourceCovoiturage rsC = new RessourceCovoiturage();
		
		return rsC.trouverParId(id);
	}
  
	// @POST => le pb est que certainement il faut produire du JSON ... à tester
	//@POST
	@Path("/ajoutTest")
	public Covoiturage ajoutTestCovoiturage(){
		RessourceCovoiturage rsC = new RessourceCovoiturage();
		
		return rsC.ajoutTestCovoiturage();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Covoiturage> listerTout() {
		RessourceCovoiturage rsC = new RessourceCovoiturage();
		
		return rsC.listerTout();
	}
	
   /* public void ajouterCovoiturage(final Covoiturage covoiturage) {
	}
    
    public Covoiturage supprimerCovoiturage(@PathParam("id") String arg0) {
		return null;
	}*/
}
