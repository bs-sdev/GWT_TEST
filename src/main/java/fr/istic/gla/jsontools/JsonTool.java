package fr.istic.gla.jsontools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

import fr.istic.gla.model.Conducteur;
import fr.istic.gla.model.Covoiturage;
import fr.istic.gla.model.Evenement;
import fr.istic.gla.model.Vehicule;

public class JsonTool {

	
	
	
	/*######################################################################
	  #																	   #
	  # 					    OUTILS DE PARSING						   #	
	  #																	   #
	  ######################################################################*/
	
	/********** PARSING COVOITURAGE **********/
	
	/**
	 * Fonction permettant de de récupérer un covoiturage et d'en 
	 * Générer un objet de type covoiturage
	 * 
	 * @param jsonToParse
	 * @param covoiturage
	 * @return
	 */
	public Covoiturage fromJson(JSONValue jsonToParse, Covoiturage covoiturage) {
		// Debug
		String debug = "";
		
		if(!jsonToParse.toString().equalsIgnoreCase("null")) {
			
			covoiturage = covoitJsonToRealobject(jsonToParse);
			
			// Debug
			debug = showCovoiturage(covoiturage);
		}
		else
		{
			Window.alert("Erreur de chargement de l'objet");
			covoiturage = null;
		}
		Window.alert("Résultat Covoit: \n " + debug);
		
		return covoiturage;
	}
	
	/**
	 * Fonction permettant de de récupérer une liste de covoiturage et d'en 
	 * Générer une liste d'objets de type covoiturage
	 * 
	 * @param jsonArrayToParse
	 * @param covoiturage
	 * @return
	 */
	public List<Covoiturage> fromJson(JSONArray jsonArrayToParse, Covoiturage covoiturage) {
		// Instanciation d'une liste vide de covoiturage qui récupérera
		// L'ensemble des covoiturages parsés plus tard dans la fonction
		List<Covoiturage> covoiturageList = new ArrayList<Covoiturage>();

		// DEBUG
		String debug = "";
		
		if (jsonArrayToParse != null) {
			int sizeArray = jsonArrayToParse.size();

			// JSONValue temporaire qui sera utile pour la boucle de parcours
			JSONValue covoitTemp;

			// Instance de covoiturage temporaire
			Covoiturage covoitReal = new Covoiturage();

			// Si le tableau renvoyé est vide (Rien à lister)
			if (sizeArray == 0) {
				Window.alert("Pas de covoiturage dans la base !");
				covoiturageList = null;
			}
			// Sinon, c'est que l'on a des éléments à lister
			else {
				// on parcours le teableau d'objet JSON reçu.
				for (int i = 0; i < sizeArray; i++) {
					covoitTemp = (JSONValue) jsonArrayToParse.get(i);

					covoitReal = covoitJsonToRealobject(covoitTemp);

					// Debug
					debug += showCovoiturage(covoitReal);

					covoiturageList.add(covoitReal);
				}
			}
		
		}
		else {
			covoiturageList = null;
		}
		Window.alert("Résultat CovoitAll: \n " + debug);
		
		return covoiturageList;
	}
	
	/********** PARSING EVENEMENT **********/
	
	/**
	 * Fonction permettant de récupérer une liste d'évènements et d'en 
	 * Générer une liste d'objets de type évènement
	 * 
	 * @param jsonArrayToParse
	 * @param evenement
	 * @return
	 */
	public List<Evenement> fromJson(JSONArray jsonArrayToParse, Evenement evenement) {
		// Instanciation d'une liste vide d'évènement qui récupérera 
		// L'ensemble des évènements parsés plus tard dans la fonction
		List<Evenement> evenementList = new ArrayList<Evenement>();
	
		// On dois retourner une liste de covoiturage ...
		int sizeArray = jsonArrayToParse.size();
	
		// JSONValue temporaire qui sera utile pour la boucle de parcours
		JSONValue evenementTemp;
		
		// Instance de covoiturage temporaire
		Evenement evenementReal = new Evenement();
		
		// DEBUG
		String debug = "";
		
		// Si le tableau renvoyé est vide (Rien à lister) 
		if(sizeArray == 0){
			Window.alert("Pas d'evenement dans la base !");
		}
		// Sinon, c'est que l'on a des éléments à lister
		else {
			//on parcours le teableau d'objet JSON reçu. 
			for(int i = 0; i < sizeArray; i++){
				evenementTemp = (JSONValue) jsonArrayToParse.get(i);

				evenementReal = evenementJsonToRealobject(evenementTemp);

				// Debug
				debug += showEvenement(evenementReal);
				
				evenementList.add(evenementReal);
			}
		}
		Window.alert("Résultat evtAll: \n " + debug);
		
		return evenementList;
	}
	
	/**
	 * Fonction permettant de récupérer un évènement et d'en 
	 * Générer un objet de type évènement
	 * 
	 * @param jsonToParse
	 * @param evenement
	 * @return
	 */
	public Evenement fromJson(JSONValue jsonToParse, Evenement evenement) {
		// Debug
		String debug = "";
		
		// Si l'objet qui arrive n'est pas vide 	
		//if(jsonToParse != null){
		if(!jsonToParse.toString().equalsIgnoreCase("null")) {
			evenement = evenementJsonToRealobject(jsonToParse);
		}
		// l'élément étant null, on y injecte un evenement dont l'id vaut 0 et dont
		// sa liste de covoiturages est vide
		else {
			evenement = null;
		}
						
		debug = showEvenement(evenement);
		Window.alert("Résultat evt: \n " + debug);
		
		return evenement;
	}
	

	
	/********** PARSING CONDUCTEUR **********/
	
	/**
	 * Fonction permettant de récupérer un conducteur et d'en 
	 * Générer un objet de type conducteur
	 * 
	 * @param jsonToParse
	 * @param conducteur
	 * @return
	 */
	public Conducteur fromJson(JSONValue jsonToParse, Conducteur conducteur) {
		
		// Debug
		String debug = "";

		// Si l'objet qui arrive n'est pas vide
		if(!jsonToParse.toString().equalsIgnoreCase("null")) {
			conducteur = conducteurJsonToRealobject(jsonToParse);
		}
		// l'élément étant null, on y injecte un conducteur dont l'id vaut 0
		else {
			conducteur = null;
		}
		
		debug = showConducteur(conducteur);
		Window.alert("Résultat conducteur: \n " + debug);
		
		return conducteur;
	}
	
	/**
	 * Fonction permettant de de récupérer une liste de covoiturage et d'en 
	 * Générer une liste d'objets de type covoiturage
	 * 
	 * @param jsonArrayToParse
	 * @param covoiturage
	 * @return
	 */
	public List<Conducteur> fromJson(JSONArray jsonArrayToParse, Conducteur conducteur) {
		// Instanciation d'une liste vide de conducteur qui récupérera
		// L'ensemble des conducteurs parsés plus tard dans la fonction
		List<Conducteur> conducteurList = new ArrayList<Conducteur>();
		
		// DEBUG
		String debug = "";

		if (jsonArrayToParse != null) {

			// On dois retourner une liste de covoiturage ...
			int sizeArray = jsonArrayToParse.size();

			// JSONValue temporaire qui sera utile pour la boucle de parcours
			JSONValue conductTemp;

			// Instance de covoiturage temporaire
			Conducteur conductReal = new Conducteur();

			// Si le tableau renvoyé est vide (Rien à lister)
			if (sizeArray == 0) {
				Window.alert("Pas de conducteur dans la base !");
			}
			// Sinon, c'est que l'on a des éléments à lister
			else {
				// on parcours le teableau d'objet JSON reçu.
				for (int i = 0; i < sizeArray; i++) {
					conductTemp = (JSONValue) jsonArrayToParse.get(i);
					
					conductReal = conducteurJsonToRealobject(conductTemp);
					// Debug
					debug += showConducteur(conductReal);

					conducteurList.add(conductReal);
				}
			}
		
		}
		Window.alert("Resultat conductAll : \n" + debug);
		
		return conducteurList;
	}
	
	/********** PARSING VEHICULE **********/
	
	/**
	 * Fonction permettant de récupérer un vehicule et d'en 
	 * Générer un objet de type Vehicule
	 * 
	 * @param jsonToParse
	 * @param vehicule
	 * @return
	 */
	public Vehicule fromJson(JSONValue jsonToParse, Vehicule vehicule) {
		// DEBUG
		String debug = "";

		// Si l'objet qui arrive n'est pas vide
		if(!jsonToParse.toString().equalsIgnoreCase("null")) {
			// Si on a l'id du vehicule au lieu de l'objet lui même 
			vehicule = vehiculeJsonToRealobject(jsonToParse);
		}
		else {
			vehicule = null;
		}
		
		debug = showVehicule(vehicule);
		Window.alert("Resultat Vehicule : \n" + debug);
		
		return vehicule;
	}
	
	/**
	 * Fonction permettant de de récupérer une liste de vehicule et d'en 
	 * Générer une liste d'objets de type vehicule
	 * 
	 * @param jsonArrayToParse
	 * @param covoiturage
	 * @return
	 */
	public List<Vehicule> fromJson(JSONArray jsonArrayToParse, Vehicule vehicule) {
		// Instanciation d'une liste vide de vehicules qui récupérera
		// L'ensemble des vehicules parsés plus tard dans la fonction
		List<Vehicule> vehiculeList = new ArrayList<Vehicule>();
		
		// DEBUG
		String debug = "";
		
		if (jsonArrayToParse != null) {

			int sizeArray = jsonArrayToParse.size();

			// JSONValue temporaire qui sera utile pour la boucle de parcours
			JSONValue vehiculeTemp;

			// Instance de covoiturage temporaire
			Vehicule vehiculeReal = new Vehicule();

			// Si le tableau renvoyé est vide (Rien à lister)
			if (sizeArray == 0) {
				Window.alert("Pas de conducteur dans la base !");
			}
			// Sinon, c'est que l'on a des éléments à lister
			else {
				// on parcours le teableau d'objet JSON reçu.
				for (int i = 0; i < sizeArray; i++) {
					vehiculeTemp = (JSONValue) jsonArrayToParse.get(i);

					vehiculeReal = vehiculeJsonToRealobject(vehiculeTemp);
					// Debug
					debug += showVehicule(vehiculeReal);

					vehiculeList.add(vehiculeReal);
				}
			}
		
		}
		
		Window.alert("Resultat vehiculeAll : \n" + debug);
		
		return vehiculeList;
	}
	
	public JSONObject toJson(Covoiturage covoiturage){
		JSONObject jsonObjectToCreate = new JSONObject();
		
		// TODO
		
		return jsonObjectToCreate;
	}
	
	
	
	public JSONObject toJson(Evenement evenement) {
		JSONObject jsonObjectToCreate = new JSONObject();
		
		// TODO
		
		return jsonObjectToCreate;
	}
	
	public JSONObject toJson(Vehicule vehicule) {
		JSONObject jsonObjectToCreate = new JSONObject();
		
		// TODO
		
		return jsonObjectToCreate;
	}
	
  /*######################################################################
	#																	 #
	# 					    OUTILS DE CONVERSION						 #	
	#																	 #
	######################################################################*/
	
	/** 
	 * Fonction permettant de transformer un covoiturage au format JSON
	 * en nouvel objet objet de type Covoiturage
	 *
	 * @param jsonValue
	 * @return
	 */
	public Covoiturage covoitJsonToRealobject(JSONValue jsonValue) {
		Covoiturage covoiturage = new Covoiturage();

		if(jsonValue instanceof JSONNumber){
			covoiturage.setId((int) ((JSONNumber) jsonValue).doubleValue());
		}
		else {
			JSONObject covoiturageJsonObject = (JSONObject) jsonValue;
			
			if(covoiturageJsonObject.get("id") != null) {
				covoiturage.setId((int) ((JSONNumber) covoiturageJsonObject.get("id")).doubleValue());
			}
			
			if(covoiturageJsonObject.get("lieuDepart") != null) {
				covoiturage.setLieuDepart(((JSONString) covoiturageJsonObject.get("lieuDepart")).stringValue());
			}
			
			if(covoiturageJsonObject.get("dateDepart") instanceof JSONNumber) {
				JSONNumber dateDepartToInsert = (JSONNumber)covoiturageJsonObject.get("dateDepart");
				Date dateToInsert = new Date(Long.parseLong(dateDepartToInsert + ""));
				covoiturage.setDateDepart(dateToInsert);
			}
			else {
				Window.alert("ERREUR DE FORMAT => " + covoiturageJsonObject.get("dateDepart"));
			}
			
			if(!covoiturageJsonObject.get("conducteur").toString().equalsIgnoreCase("null")) {
				covoiturage.setConducteur((this.fromJson((JSONValue) covoiturageJsonObject.get("conducteur"), new Conducteur())));
			}
			else {
				covoiturage.setConducteur(null);
			}
			
			if(!covoiturageJsonObject.get("evenement").toString().equalsIgnoreCase("null")) {
				covoiturage.setEvenement((this.fromJson((JSONValue) covoiturageJsonObject.get("evenement"), new Evenement())));
			}
			else {
				covoiturage.setEvenement(null);
			}
		}

		return covoiturage;
	}
	
	/**
	 * Fonction permettant de transformer un evenement au format JSON
	 * en nouvel objet objet de type Evenement
	 * 
	 * @param jsonValue
	 * @return
	 */
	public Evenement evenementJsonToRealobject(JSONValue jsonValue) {
		Evenement evenement = new Evenement();

		if(jsonValue instanceof JSONNumber){
			evenement.setId((int) ((JSONNumber) jsonValue).doubleValue());
		}
		else {
			JSONObject evenementJsonObject = (JSONObject) jsonValue;
			
			if(evenementJsonObject.get("id") != null) {
				evenement.setId((int) ((JSONNumber) evenementJsonObject.get("id")).doubleValue());
			}
			
			if(evenementJsonObject.get("lieuEvt") != null) {
				evenement.setLieuEvt(((JSONString) evenementJsonObject.get("lieuEvt")).stringValue());
			}
			
			if(evenementJsonObject.get("covoiturage") != null) {
				//Window.alert("Evenement : listeCovoiturage non null : " + evenementJsonObject.get("covoiturage").toString());
				List<Covoiturage> listCovoiturageToInsert = this.fromJson((JSONArray)((JSONObject) jsonValue).get("covoiturage"), new Covoiturage());
				evenement.setCovoiturage(listCovoiturageToInsert);
			}
			else {
				evenement.setCovoiturage(null);
			}
		}

		return evenement;
	}
	
	/** 
	 * Fonction permettant de transformer un covoiturage au format JSON
	 * en nouvel objet objet de type Covoiturage
	 *
	 * @param jsonValue
	 * @return
	 */
	public Conducteur conducteurJsonToRealobject(JSONValue jsonValue) {
		Conducteur conducteur = new Conducteur();

		// Si la valeur en JSON est un entier car l'identiant de l'entier
		if(jsonValue instanceof JSONNumber){
			conducteur.setId((int) ((JSONNumber) jsonValue).doubleValue());
		}
		else if(jsonValue instanceof JSONObject){
			JSONObject conductJsonObject = (JSONObject) jsonValue;
			
			if(conductJsonObject.get("id") != null) {
				conducteur.setId((int) ((JSONNumber) conductJsonObject.get("id")).doubleValue());
			}
			
			if(conductJsonObject.get("nom") != null) {
				conducteur.setNom(((JSONString) (conductJsonObject.get("nom"))).stringValue());
			}
			
			if(conductJsonObject.get("prenom") != null) {
				conducteur.setPrenom(((JSONString) (conductJsonObject.get("prenom"))).stringValue());
			}
			
			if(conductJsonObject.get("dateNaissance") != null) {
				JSONNumber dateNaissanceToInsert = (JSONNumber)conductJsonObject.get("dateNaissance");
				Date dateToInsert = new Date(Long.parseLong(dateNaissanceToInsert + ""));
				conducteur.setDateNaissance(dateToInsert);
			}
			
			if(!conductJsonObject.get("covoiturages").toString().equalsIgnoreCase("null")) {
				List<Covoiturage> listCovoiturageToInsert = this.fromJson((JSONArray)conductJsonObject.get("covoiturages"), new Covoiturage());
				conducteur.setCovoiturages(listCovoiturageToInsert);
			}
			else {
				conducteur.setCovoiturages(null);
			}
			
			if(conductJsonObject.get("mail") != null) {
				conducteur.setMail(((JSONString) (conductJsonObject.get("mail"))).stringValue());
			}
			
			if(!conductJsonObject.get("vehicule").toString().equalsIgnoreCase("null")){
				conducteur.setVehicule(this.fromJson((JSONValue) conductJsonObject.get("vehicule"), new Vehicule()));
			}
			else {
				conducteur.setVehicule(null);
			}
			
		}

		//Window.alert("fin de conducteurJsonToRealobject");
		
		return conducteur;
	}
	
	/**
	 * Fonction permettant de transformer un vehicule au format JSON
	 * en nouvel objet objet de type Vehicule
	 * 
	 * @param jsonValue
	 * @return
	 */
	public Vehicule vehiculeJsonToRealobject(JSONValue jsonValue) {
		Vehicule vehicule = new Vehicule();

		if (jsonValue instanceof JSONNumber) {
			vehicule.setId((int) ((JSONNumber) jsonValue).doubleValue());
		} else if (jsonValue instanceof JSONObject) {

			JSONObject vehiculeJsonObject = (JSONObject) jsonValue;

			if (vehiculeJsonObject.get("id") != null) {
				vehicule.setId((int) ((JSONNumber) vehiculeJsonObject.get("id"))
						.doubleValue());
			}

			if (vehiculeJsonObject.get("marque") != null) {
				vehicule.setMarque(((JSONString) (vehiculeJsonObject
						.get("marque"))).stringValue());
			}

			if (vehiculeJsonObject.get("modele") != null) {
				vehicule.setModele(((JSONString) (vehiculeJsonObject
						.get("modele"))).stringValue());
			}

			if (vehiculeJsonObject.get("nbPlace") != null) {
				vehicule.setNbPlace((int) ((JSONNumber) vehiculeJsonObject
						.get("nbPlace")).doubleValue());
			}

			if (!vehiculeJsonObject.get("conducteur").toString().equalsIgnoreCase("null")) {
				vehicule.setConducteur((this.fromJson(
						(JSONValue) vehiculeJsonObject.get("conducteur"),
						new Conducteur())));
			} else {
				vehicule.setConducteur(null);
				//Window.alert("Vehicule : conducteur null");
			}

			if (!vehiculeJsonObject.get("covoiturage").toString().equalsIgnoreCase("null")) {
				vehicule.setCovoiturage((this.fromJson(
						(JSONValue) vehiculeJsonObject.get("covoiturage"),
						new Covoiturage())));
			} else {
				vehicule.setCovoiturage(null);
			}
		}

		return vehicule;
	}
	
	
  /*######################################################################
	#																	 #
	# 						  OUTILS DE DEBUG							 #	
	#																	 #
	######################################################################*/
	
	// DEBUG : permet l'affichage d'un covoiturage dans une popup
	/**
	 * Fonction permettant de récupérer dans une String 
	 * le contenu d'un Covoiturage
	 * 
	 * @param covoiturage
	 * @return
	 */
	public String showCovoiturage(Covoiturage covoiturage) {
		String result = "";

		if(covoiturage != null) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\ncovoiturage:id: ").append(covoiturage.getId())
			.append("\ncovoiturage:lieuDepart: ").append(covoiturage.getLieuDepart())
			.append("\ncovoiturage:dateDepart: ").append(covoiturage.getDateDepart())
			.append("\ncovoiturage:conducteur: ").append(covoiturage.getConducteur() == null ? "null" : showConducteur(covoiturage.getConducteur()))
			.append("\ncovoiturage:evenement: ").append(covoiturage.getEvenement() == null ? "null" : showEvenement(covoiturage.getEvenement()));
			
			result = sb.toString();
		}
		else {
			result = "null";
		}
		
		return result;
	}
	
	// DEBUG : permet l'affichage d'un covoiturage dans une popup
	/**
	 * Fonction permettant de récupérer dans une String 
	 * le contenu d'un Conducteur
	 * 
	 * @param conducteur
	 * @return
	 */
	public String showConducteur(Conducteur conducteur) {
		
		String listCovoiturage = "";
		String result = "";

		if(conducteur != null){
			if(conducteur.getCovoiturages() != null) {
				for (int i = 0; i < conducteur.getCovoiturages().size(); i++) {
					listCovoiturage += showCovoiturage(conducteur.getCovoiturages()
							.get(i));
				}
			}
			else {
				listCovoiturage = "null";
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("\nconducteur:id: ").append(conducteur.getId())
			.append("\nconducteur:nom: ").append(conducteur.getNom())
			.append("\nconducteur:prenom: ").append(conducteur.getPrenom())
			.append("\nconducteur:dateNaissance: ").append(conducteur.getDateNaissance())
			.append("\nconducteur:covoiturages: ").append(listCovoiturage)
			.append("\nconducteur:mail: ").append(conducteur.getMail());
			sb.append("\nconducteur:vehicule: ").append(conducteur.getVehicule() == null ? "null" : showVehicule(conducteur.getVehicule()));
			
			result = sb.toString();
		}
		else {
			result = "null";
		}

		return result;
	}
	
	// DEBUG : permet l'affichage d'un evenement dans une popup
	/**
	 * Fonction permettant de récupérer dans une String 
	 * le contenu d'un Evenement
	 * 
	 * @param evenement
	 * @return
	 */
	public String showEvenement(Evenement evenement) {
		
		String result = "";
		
		if(evenement != null) {
			String listCovoiturage = ""; 
			
			if(evenement.getCovoiturage() != null) {
				for(int i = 0; i < evenement.getCovoiturage().size(); i++) {
					listCovoiturage += showCovoiturage(evenement.getCovoiturage().get(i));
				}
			} 
			else {
				listCovoiturage = "null";
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("\nevenement:id: ").append(evenement.getId())
			.append("\nevenement:lieuDepart: ").append(evenement.getLieuEvt())
			.append("\nevenement:covoiturage: ").append(listCovoiturage);
			
			result = sb.toString();
		}
		else {
			result = "null";
		}
		
		return result;
	}
	
	// DEBUG : permet l'affichage d'un vehicule dans une popup
	/**
	 * Fonction permettant de récupérer dans une String 
	 * le contenu d'un vehicule
	 * 
	 * @param vehicule
	 * @return
	 */
	public String showVehicule(Vehicule vehicule) {
		String result = "";
		
		if(vehicule != null){
			StringBuilder sb = new StringBuilder();
			
			sb.append("\nvehicule:id: ").append(vehicule.getId())
			.append("\nvehicule:marque: ").append(vehicule.getMarque())
			.append("\nvehicule:modele: ").append(vehicule.getModele())
			.append("\nvehicule:conducteur: ").append(vehicule.getConducteur() == null ? "null" : showConducteur(vehicule.getConducteur()))
			.append("\nvehicule:covoiturages: ").append(vehicule.getCovoiturage() == null ? "null" : showCovoiturage(vehicule.getCovoiturage()));
			
			result = sb.toString();
		}
		else {
			result = "null";
		}

		return result;
	}
	
	  /*######################################################################
		#																	 #
		# 						  OUTILS VERIFICATION						 #	
		#																	 #
		######################################################################*/
	
	/**
	 * Fonction permettant de déterminer si l'objet passé en paramètre (en String)
	 * Est de type entier ou objet
	 * 
	 * @param object
	 * @return
	 */
	public boolean isInteger(String object) {
		if(Integer.parseInt(object) >= 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
